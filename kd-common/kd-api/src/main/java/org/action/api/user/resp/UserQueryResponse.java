package org.action.api.user.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.base.response.BaseResponse;

import java.io.Serial;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryResponse<T>  extends BaseResponse {


    @Serial
    private static final long serialVersionUID = 1L;

    private T data;
}
