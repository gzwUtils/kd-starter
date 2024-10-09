package org.action.exception;

import org.action.enums.ErrorCode;


/**
 * @author gzw
 * @create 2024/9/30 16:16
 */
public class PlatException extends BizException {

    public PlatException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PlatException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public PlatException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public PlatException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public PlatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

}
