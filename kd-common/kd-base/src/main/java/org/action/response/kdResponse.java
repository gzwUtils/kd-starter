package org.action.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gzw
 * @description：
 * @since：2024/7/31 00:11
 */
@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true)
@Data
public class kdResponse<T> extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private T data;

    public static <T> kdResponse<T> ok(T data) {
        kdResponse<T> kdResponse = new kdResponse<>();
        kdResponse.setData(data);
        kdResponse.setSuccess(true);
        return kdResponse;
    }

    public static <T> kdResponse<T> fail(String errorMessage,String errorCode) {
        kdResponse<T> kdResponse = new kdResponse<>();
        kdResponse.setSuccess(false);
        kdResponse.setMsg(errorMessage);
        kdResponse.setCode(errorCode);
        return kdResponse;
    }
}
