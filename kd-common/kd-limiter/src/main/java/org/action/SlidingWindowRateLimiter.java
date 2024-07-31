package org.action;

import static org.action.constant.LimitConstant.LIMIT_KEY_PREFIX;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

/**
 * @author gzw
 * @description： 滑动窗口
 * @since：2024/7/30 21:33
 */
public class SlidingWindowRateLimiter implements KdRateLimiter {

    private final RedissonClient redissonClient;


    public SlidingWindowRateLimiter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Boolean tryAcquire(String key, int limit, int windowSize) {
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter(LIMIT_KEY_PREFIX + key);
        if (!rRateLimiter.isExists()) {
            rRateLimiter.trySetRate(RateType.OVERALL, limit, windowSize, RateIntervalUnit.SECONDS);
        }
        return rRateLimiter.tryAcquire();
    }
}
