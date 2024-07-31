package org.action.response;

import java.io.Serializable;
import lombok.Data;

/**
 * @author gzw
 * @description：  base resp
 * @since：2024/7/31 00:06
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private Boolean success;
}
