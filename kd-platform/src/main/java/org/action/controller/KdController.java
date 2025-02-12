package org.action.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.action.SlidingWindowRateLimiter;
import static org.action.constant.Constant.DEFAULT_LOGIN_SESSION_TIMEOUT;
import static org.action.enums.AuthErrorCode.VERIFICATION_CODE_WRONG;
import org.action.enums.RepoErrorCode;
import org.action.exception.AuthException;
import org.action.exception.PlatException;
import static org.action.notice.constant.NoticeConstant.CAPTCHA_KEY_PREFIX;
import org.action.notice.req.SendInput;
import org.action.notice.resp.NoticeResponse;
import org.action.notice.service.NoticeKdCadeService;
import org.action.param.LoginParam;
import org.action.param.RegisterParam;
import org.action.thread.ThreadTask;
import org.action.user.req.UserQueryRequest;
import org.action.user.req.UserRegisterRequest;
import org.action.user.resp.UserOperatorResponse;
import org.action.user.resp.UserQueryResponse;
import org.action.user.resp.data.UserInfo;
import org.action.user.service.UserFacadeService;
import org.action.vo.LoginVO;
import org.action.web.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author gzw
 * @description： thread
 * @since：2024/4/24 02:23
 */

@RestController
@RequestMapping("/thr")
public class KdController {

    @Resource
    private ThreadTask threadTask;

    @Resource
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    @DubboReference(version = "1.0.0")
    private NoticeKdCadeService noticeKdCadeService;

    @DubboReference(version = "1.0.0")
    private UserFacadeService userFacadeService;

    @Resource
    private StringRedisTemplate redisTemplate;

    @PostMapping(value = "/send",produces = "application/json;charset=utf-8")
    public Result<?> send (@RequestParam("key") String key){
        Boolean access = slidingWindowRateLimiter.tryAcquire(key, 1, 60);

        if (Boolean.FALSE.equals(access)) {
            throw new PlatException(RepoErrorCode.SEND_NOTICE_DUPLICATED);
        }
        threadTask.send();
        return Result.success();
    }


    @PostMapping(value = "/sendMsg",produces = "application/json;charset=utf-8")
    public Result<?> sendMsg (@RequestBody SendInput sendInput){
        NoticeResponse response = noticeKdCadeService.generateModelContext(sendInput);

        if (Boolean.FALSE.equals(response.getSuccess())) {
            throw new PlatException(RepoErrorCode.SMS_FAIL);
        }
        return Result.success();
    }


    @PostMapping(value = "/sendCaptcha",produces = "application/json;charset=utf-8")
    public Result<?> sendCaptcha (@RequestParam String phone){
        NoticeResponse response = noticeKdCadeService.generateAndSendSmsCaptcha(phone);

        if (Boolean.FALSE.equals(response.getSuccess())) {
            throw new PlatException(RepoErrorCode.SMS_FAIL);
        }
        return Result.success();
    }


    @PostMapping("/register")
    public Result<Boolean> register(@Valid @RequestBody RegisterParam registerParam) {

        //验证码校验
        String cachedCode = redisTemplate.opsForValue().get(CAPTCHA_KEY_PREFIX + registerParam.getTelephone());
        if (!StringUtils.equalsIgnoreCase(cachedCode, registerParam.getCaptcha())) {
            throw new AuthException(VERIFICATION_CODE_WRONG);
        }

        //注册
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setTelephone(registerParam.getTelephone());
        userRegisterRequest.setInviteCode(registerParam.getInviteCode());

        UserOperatorResponse registerResult = userFacadeService.register(userRegisterRequest);
        if(registerResult.getSuccess()){
            return Result.success(true);
        }
        return Result.error(registerResult.getCode(), registerResult.getMsg());
    }

    /**
     * 登录方法
     *
     * @param loginParam 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginParam loginParam) {
        //验证码校验
        String cachedCode = redisTemplate.opsForValue().get(CAPTCHA_KEY_PREFIX + loginParam.getTelephone());
        if (!StringUtils.equalsIgnoreCase(cachedCode, loginParam.getCaptcha())) {
            throw new AuthException(VERIFICATION_CODE_WRONG);
        }
        //判断是注册还是登陆
        //查询用户信息
        UserQueryRequest userQueryRequest = new UserQueryRequest(loginParam.getTelephone());
        UserQueryResponse<UserInfo> userQueryResponse = userFacadeService.query(userQueryRequest);
        UserInfo userInfo = userQueryResponse.getData();
        if (userInfo == null) {
            //需要注册
            UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
            userRegisterRequest.setTelephone(loginParam.getTelephone());
            userRegisterRequest.setInviteCode(loginParam.getInviteCode());

            UserOperatorResponse response = userFacadeService.register(userRegisterRequest);
            if (response.getSuccess()) {
                userQueryResponse = userFacadeService.query(userQueryRequest);
                userInfo = userQueryResponse.getData();
                StpUtil.login(userInfo.getUserId(), new SaLoginModel().setIsLastingCookie(loginParam.getRememberMe())
                        .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
                StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
                LoginVO loginVO = new LoginVO(userInfo);
                return Result.success(loginVO);
            }

            return Result.error(response.getCode(), response.getMsg());
        } else {
            //登录
            StpUtil.login(userInfo.getUserId(), new SaLoginModel().setIsLastingCookie(loginParam.getRememberMe())
                    .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
            StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
            LoginVO loginVO = new LoginVO(userInfo);
            return Result.success(loginVO);
        }
    }

    @PostMapping("/logout")
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.success(true);
    }
}
