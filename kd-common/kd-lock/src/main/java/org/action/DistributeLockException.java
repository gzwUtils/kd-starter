package org.action;


/**
 * @author gzw
 * @description： 分布式锁异常
 * @since：2024/7/31 00:36
 */
public class DistributeLockException extends RuntimeException {

    public DistributeLockException() {
    }

    public DistributeLockException(String message) {
        super(message);
    }

    public DistributeLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributeLockException(Throwable cause) {
        super(cause);
    }

    public DistributeLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
