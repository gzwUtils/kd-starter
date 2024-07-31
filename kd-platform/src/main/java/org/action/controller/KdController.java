package org.action.controller;

import javax.annotation.Resource;
import org.action.SlidingWindowRateLimiter;
import org.action.enums.RepoErrorCode;
import org.action.exception.BizException;
import org.action.thread.ThreadTask;
import org.action.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/send",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Result<?> send (@RequestParam("key") String key){
        Boolean access = slidingWindowRateLimiter.tryAcquire(key, 1, 60);

        if (!access) {
            throw new BizException(RepoErrorCode.DUPLICATED);
        }
        threadTask.send();
        return Result.success();
    }
}
