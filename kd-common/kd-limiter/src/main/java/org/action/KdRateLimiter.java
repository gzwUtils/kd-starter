package org.action;

/**
 * @author gzw
 * @description： 限流服务
 * @since：2024/7/30 21:32
 */
public interface KdRateLimiter {


    /**
     * 判断key是否通过
     *
     * @param key 限流的key
     * @param limit 限流的数量
     * @param windowSize 窗口大小，单位为秒
     * @return true/false
     */
    public Boolean tryAcquire(String key, int limit, int windowSize);

}
