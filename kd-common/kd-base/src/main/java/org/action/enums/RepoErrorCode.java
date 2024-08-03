package org.action.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gzw
 * @description： 日常通用错误码
 * @since：2024/7/30 22:36
 */
@AllArgsConstructor
@Getter
public enum RepoErrorCode implements ErrorCode {


    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知错误"),


    /**
     * 重复请求
     */
    DUPLICATED("DUPLICATED", "重复请求"),


    /**
     * 短信发送异常
     */
    SMS_FAIL("SMS_FAIL", "短信发送异常"),


    /**
     * 通知保存失败
     */
    NOTICE_SAVE_FAILED("NOTICE_SAVE_FAILED", "通知保存失败"),

    /**
     * 不允许重复发送通知
     */
    SEND_NOTICE_DUPLICATED("SEND_NOTICE_DUPLICATED", "不允许重复发送通知"),

    /**
     * 短信模版不存在
     */
    TEMPLATE_NOT_FOUND("TEMPLATE_NOT_FOUND", "短信模版不存在"),

    /**
     * 参数校验错误
     */
    PARAM_VALID_ERROR("PARAM_VALID_ERROR", "参数校验错误");

    private final String code;

    private final String message;

}
