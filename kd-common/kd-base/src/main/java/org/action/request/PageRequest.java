package org.action.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gzw
 * @description：
 * @since：2024/7/31 00:02
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class PageRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * current page
     */
    private Integer page;

    /**
     * 每页结果数
     */

    private Integer size;

}
