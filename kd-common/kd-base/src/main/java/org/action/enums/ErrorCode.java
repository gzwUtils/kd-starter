package org.action.enums;

/**
 * @author gzw
 * @description： 错误码
 * @since：2024/7/30 22:13
 */
public interface ErrorCode {

    /**
     * @return 错误码
     */
    String getCode();

    /**
     * @return 错误信息
     */
    String getMessage();
}
