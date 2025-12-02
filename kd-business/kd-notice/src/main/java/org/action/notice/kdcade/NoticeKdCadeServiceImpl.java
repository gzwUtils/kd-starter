package org.action.notice.kdcade;
import cn.hutool.core.util.RandomUtil;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.action.limiter.SlidingWindowRateLimiter;
import org.action.rpc.kdCade.KdCade;
import org.action.sms.SmsService;
import org.action.notice.entity.Notice;
import org.action.notice.enums.NoticeState;
import static org.action.base.enums.RepoErrorCode.SEND_NOTICE_DUPLICATED;
import static org.action.api.notice.constant.NoticeConstant.CAPTCHA_KEY_PREFIX;
import org.action.base.exception.SystemException;
import org.action.api.notice.resp.NoticeResponse;
import org.action.api.notice.service.NoticeKdCadeService;
import org.action.sms.resp.SmsSendResponse;
import org.action.notice.service.NoticeService;
import org.action.notice.service.TemplateService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:32
 */
@DubboService(version = "1.0.0")
public class NoticeKdCadeServiceImpl implements NoticeKdCadeService {

    @Resource
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    @Resource
    private NoticeService noticeService;

    @Resource
    private SmsService smsService;

    @Resource
    private TemplateService templateService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @KdCade
    public NoticeResponse generateAndSendSmsCaptcha(String telephone) {
        Boolean access = slidingWindowRateLimiter.tryAcquire(telephone, 1, 60);

        if (Boolean.FALSE.equals(access)) {
            throw new SystemException(SEND_NOTICE_DUPLICATED);
        }

        // 生成验证码
        String captcha = RandomUtil.randomNumbers(4);

        // 验证码存入Redis
        redisTemplate.opsForValue().set(CAPTCHA_KEY_PREFIX + telephone, captcha, 5, TimeUnit.MINUTES);

        Notice notice = noticeService.saveCaptcha(telephone, captcha);

        Thread.ofVirtual().start(() -> {
            SmsSendResponse result = smsService.sendMsg(notice.getTargetAddress(), notice.getNoticeContent());
            if (Boolean.TRUE.equals(result.getSuccess())) {
                notice.setState(NoticeState.SUCCESS);
                notice.setSendSuccessTime(new Date());
                noticeService.updateById(notice);
            } else {
                notice.setState(NoticeState.FAILED);
                notice.addExtendInfo("executeResult", com.alibaba.fastjson.JSON.toJSONString(result));
                noticeService.updateById(notice);
            }
        });

        return new NoticeResponse.Builder().setSuccess(true).build();
    }



}
