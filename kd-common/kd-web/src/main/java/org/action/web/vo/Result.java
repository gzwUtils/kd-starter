package org.action.web.vo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.enums.RepoErrorCode;
import org.action.response.kdResponse;

/**
 * @author gzw
 * @description： resp
 * @since：2024/4/24 02:23
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
@Data
public class Result<T> extends kdResponse<T>{


    public static <T> Result<T> success(){

        return success(RepoErrorCode.SUCCESS);
    }

    public static <T> Result<T>  success(RepoErrorCode resultCodeEnum) {
        return success(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    public static <T> Result<T> success(T data) {
        return success(RepoErrorCode.SUCCESS.getCode(), RepoErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String code, String message, T data) {
        Result<T> response = new Result<>();
        response.setCode(code);
        response.setMsg(message);
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static <T> Result<T> error(String code, String message, T data) {
        Result<T> response = new Result<>();
        response.setCode(code);
        response.setMsg(message);
        response.setData(data);
        response.setSuccess(false);
        return response;
    }

    public static <T> Result<T> error(RepoErrorCode resultCodeEnum) {
        return error(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static <T> Result<T> error(String code, String message) {
        return error(code, message, null);
    }
}
