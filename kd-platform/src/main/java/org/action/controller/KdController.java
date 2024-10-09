package org.action.controller;

import javax.annotation.Resource;
import org.action.SlidingWindowRateLimiter;
import org.action.enums.RepoErrorCode;
import org.action.exception.PlatException;
import org.action.notice.req.SendInput;
import org.action.notice.resp.NoticeResponse;
import org.action.notice.service.NoticeKdCadeService;
import org.action.thread.ThreadTask;
import org.action.web.vo.Result;
import org.apache.dubbo.config.annotation.DubboReference;
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
}
