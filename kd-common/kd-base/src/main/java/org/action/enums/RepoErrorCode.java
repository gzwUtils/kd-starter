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
     * 参数校验错误
     */
    PARAM_VALID_ERROR("PARAM_VALID_ERROR", "参数校验错误");

    private final String code;

    private final String message;

}
