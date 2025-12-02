package org.action.user.facade.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.template.QuickConfig;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.action.api.user.enums.UserOperateTypeEnum;
import org.action.api.user.resp.UserOperatorResponse;
import org.action.lock.DistributeLock;
import org.action.base.exception.BizException;
import org.action.user.common.entity.User;
import org.action.user.common.enums.UserErrorCode;
import static org.action.user.common.enums.UserErrorCode.DUPLICATE_TELEPHONE_NUMBER;
import org.action.user.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.action.base.enums.RepoErrorCode;
import  org.action.user.exception.UserException;

/**
 * @author gzw
 * @description：
 * @since：2025/2/12 23:05
 */

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements InitializingBean {


    private static final String DEFAULT_NICK_NAME_PREFIX = "藏家_";

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserOperateStreamService userOperateStreamService;

    @Resource
    private RedissonClient redissonClient;

    private RBloomFilter<String> nickNameBloomFilter;

    private RBloomFilter<String> inviteCodeBloomFilter;

    private RScoredSortedSet<String> inviteRank;

    @Resource
    private CacheManager cacheManager;

    private Cache<String, User> idUserCache;


    @PostConstruct
    public void init() {
        QuickConfig idQc = QuickConfig.newBuilder(":user:cache:id:")
                .cacheType(CacheType.BOTH)
                .expire(Duration.ofHours(2))
                .syncLocal(true)
                .build();
        idUserCache = cacheManager.getOrCreateCache(idQc);
    }

    @DistributeLock(keyExpression = "#telephone", scene = "USER_REGISTER")
    @Transactional
    public UserOperatorResponse register(String telephone, String inviteCode) {
        String defaultNickName;
        String randomString;
        do {
            randomString = RandomUtil.randomString(6).toUpperCase();
            //前缀 + 6位随机数 + 手机号后四位
            defaultNickName = DEFAULT_NICK_NAME_PREFIX + randomString + telephone.substring(7, 11);
        } while (nickNameExist(defaultNickName) || inviteCodeExist(randomString));

        String inviterId = null;
        if (StringUtils.isNotBlank(inviteCode)) {
            User inviter = userMapper.findByInviteCode(inviteCode);
            if (inviter != null) {
                inviterId = inviter.getId().toString();
            }
        }

        User user = register(telephone, defaultNickName, telephone, randomString, inviterId);
        Assert.notNull(user, UserErrorCode.USER_OPERATE_FAILED.getCode());

        addNickName(defaultNickName);
        addInviteCode(randomString);
        updateInviteRank(inviterId);
        updateUserCache(user.getId().toString(), user);

        //加入流水
        long streamResult = userOperateStreamService.insertStream(user, UserOperateTypeEnum.REGISTER);
        Assert.notNull(streamResult, () -> new BizException(RepoErrorCode.UPDATE_FAILED));

        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();
        userOperatorResponse.setSuccess(true);

        return userOperatorResponse;
    }


    /**
     * 注册
     *
     * @param telephone tel
     * @param nickName nick
     * @param password pass
     * @return user
     */
    private User register(String telephone, String nickName, String password, String inviteCode, String inviterId) {
        if (userMapper.findByTelephone(telephone) != null) {
            throw new UserException(DUPLICATE_TELEPHONE_NUMBER);
        }

        User user = new User();
        user.register(telephone, nickName, password, inviteCode, inviterId);
        return save(user) ? user : null;
    }


    public boolean nickNameExist(String nickName) {
        //如果布隆过滤器中存在，再进行数据库二次判断
        if (this.nickNameBloomFilter != null && this.nickNameBloomFilter.contains(nickName)) {
            return userMapper.findByNickname(nickName) != null;
        }

        return false;
    }

    public boolean inviteCodeExist(String inviteCode) {
        //如果布隆过滤器中存在，再进行数据库二次判断
        if (this.inviteCodeBloomFilter != null && this.inviteCodeBloomFilter.contains(inviteCode)) {
            return userMapper.findByInviteCode(inviteCode) != null;
        }

        return false;
    }

    private boolean addNickName(String nickName) {
        return this.nickNameBloomFilter != null && this.nickNameBloomFilter.add(nickName);
    }

    private boolean addInviteCode(String inviteCode) {
        return this.inviteCodeBloomFilter != null && this.inviteCodeBloomFilter.add(inviteCode);
    }

    private void updateInviteRank(String inviterId) {
        if (inviterId == null) {
            return;
        }
        RLock rLock = redissonClient.getLock(inviterId);
        rLock.lock();
        try {
            Double score = inviteRank.getScore(inviterId);
            if (score == null) {
                score = 0.0;
            }
            inviteRank.add(score + 100.0, inviterId);
        } finally {
            rLock.unlock();
        }
    }

    private void updateUserCache(String userId, User user) {
        idUserCache.put(userId, user);
    }


    /**
     * 通过用户ID查询用户信息
     *
     * @param userId id
     * @return user
     */
    @Cached(name = ":user:cache:id:", cacheType = CacheType.BOTH, key = "#userId", cacheNullValue = true)
    @CacheRefresh(refresh = 60, timeUnit = TimeUnit.MINUTES)
    public User findById(Long userId) {
        return userMapper.findById(userId);
    }

    /**
     * 通过手机号查询用户信息
     *
     * @param telephone tel
     * @return user
     */
    public User findByTelephone(String telephone) {
        return userMapper.findByTelephone(telephone);
    }


    @Override
    public void afterPropertiesSet() {
        this.nickNameBloomFilter = redissonClient.getBloomFilter("nickName");
        if (nickNameBloomFilter != null && !nickNameBloomFilter.isExists()) {
            this.nickNameBloomFilter.tryInit(100000L, 0.01);
        }

        this.inviteCodeBloomFilter = redissonClient.getBloomFilter("inviteCode");
        if (inviteCodeBloomFilter != null && !inviteCodeBloomFilter.isExists()) {
            this.inviteCodeBloomFilter.tryInit(100000L, 0.01);
        }

        this.inviteRank = redissonClient.getScoredSortedSet("inviteRank");
    }
}
