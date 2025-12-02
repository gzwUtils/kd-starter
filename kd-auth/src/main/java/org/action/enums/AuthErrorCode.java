package org.action.enums;

import lombok.Getter;
import org.action.base.enums.ErrorCode;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 22:33
 */

@Getter
public enum AuthErrorCode implements ErrorCode {

    /**
     * 用户状态不可用
     */
    USER_STATUS_IS_NOT_ACTIVE("USER_STATUS_IS_NOT_ACTIVE", "用户状态不可用"),

    /**
     * 验证码错误
     */
    VERIFICATION_CODE_WRONG("VERIFICATION_CODE_WRONG", "验证码错误"),

    /**
     * 用户信息查询失败
     */
    USER_QUERY_FAILED("USER_QUERY_FAILED", "用户信息查询失败"),

    /**
     * 用户未登录
     */
    USER_NOT_LOGIN("USER_NOT_LOGIN", "用户未登录"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在");

    private final String code;

    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
