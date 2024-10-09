package org.action.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.action.exception.BizException;
import org.action.exception.SystemException;
import org.action.web.vo.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.action.enums.RepoErrorCode.PARAM_VALID_ERROR;

/**
 * @author gzw
 * @description： global web exception
 * @since：2024/7/31 21:35
 */
@Slf4j
@RestControllerAdvice
public class GlobalWebExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> Result<T> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder buffer = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            buffer.append(fieldName).append(":").append(errorMessage).append(",");
        });
        return  Result.error(PARAM_VALID_ERROR.getCode(),buffer.toString());
    }

    /**
     * 业务异常处理器
     * @param bizException biz
     */
    @ExceptionHandler(BizException.class)
    public <T> Result<T> exceptionHandler(BizException bizException) {
        return Result.error(bizException.getErrorCode().getCode(), bizException.getErrorCode().getMessage());
    }

    /**
     * 系统异常处理器
     * @param systemException sys
     */
    @ExceptionHandler(SystemException.class)
    public <T> Result<T> systemExceptionHandler(SystemException systemException) {
        return Result.error(systemException.getErrorCode().getCode(), systemException.getErrorCode().getMessage());
    }

}
