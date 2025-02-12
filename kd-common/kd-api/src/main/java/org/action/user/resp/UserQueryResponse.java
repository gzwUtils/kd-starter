package org.action.user.resp;

import lombok.Data;
import org.action.response.BaseResponse;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:41
 */
@Data
public class UserQueryResponse<T>  extends BaseResponse {


    private static final long serialVersionUID = 1L;

    private T data;
}
