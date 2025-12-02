package org.action.base.response;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * @author gzw
 * @description：  base resp
 * @since：2024/7/31 00:06
 */
@Data
public class BaseResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private Boolean success ;
}
