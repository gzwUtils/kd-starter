package org.action.controller;

import javax.annotation.Resource;
import org.action.SlidingWindowRateLimiter;
import org.action.enums.RepoErrorCode;
import org.action.exception.BizException;
import org.action.notice.req.SendInput;
import org.action.notice.resp.NoticeResponse;
import org.action.notice.service.NoticeKdCadeService;
import org.action.thread.ThreadTask;
import org.action.vo.Result;
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

    @RequestMapping(value = "/send",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Result<?> send (@RequestParam("key") String key){
        Boolean access = slidingWindowRateLimiter.tryAcquire(key, 1, 60);

        if (!access) {
            throw new BizException(RepoErrorCode.SEND_NOTICE_DUPLICATED);
        }
        threadTask.send();
        return Result.success();
    }


    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Result<?> sendMsg (@RequestBody SendInput sendInput){
        NoticeResponse response = noticeKdCadeService.generateAndSendSmsCaptcha(sendInput);

        if (!response.getSuccess()) {
            throw new BizException(RepoErrorCode.SMS_FAIL);
        }
        return Result.success();
    }
}
