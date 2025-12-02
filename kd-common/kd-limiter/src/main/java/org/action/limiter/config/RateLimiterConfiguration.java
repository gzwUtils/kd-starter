package org.action.limiter.config;

import org.action.limiter.SlidingWindowRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gzw
 * @description：
 * @since：2024/7/30 21:43
 */

@Configuration
public class RateLimiterConfiguration {

    @Bean
    public SlidingWindowRateLimiter slidingWindowRateLimiter(RedissonClient redissonClient) {

        return new SlidingWindowRateLimiter(redissonClient);
    }

}
