package org.action.controller;


import cn.dev33.satoken.stp.StpUtil;
import jakarta.validation.constraints.NotBlank;
import org.action.enums.AuthErrorCode;
import org.action.exception.AuthException;
import org.action.web.vo.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.action.cache.constant.CacheConstant.CACHE_PREFIX;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 22:09
 */

@RestController
@RequestMapping("/token")
public class TokenController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String TOKEN_PREFIX = "token:";

    @GetMapping("/get")
    public Result<String> get(@NotBlank String scene) {
        if (StpUtil.isLogin()) {
            String token = UUID.randomUUID().toString();
            String tokenKey = TOKEN_PREFIX + scene + CACHE_PREFIX + token;
            redisTemplate.opsForValue().set(tokenKey, token, 30, TimeUnit.MINUTES);
            return Result.success(tokenKey);
        }
        throw new AuthException(AuthErrorCode.USER_NOT_LOGIN);
    }

}
