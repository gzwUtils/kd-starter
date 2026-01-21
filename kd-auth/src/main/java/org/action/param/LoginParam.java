package org.action.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:10
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginParam extends RegisterParam {

    /**
     * 记住我
     */
    private Boolean rememberMe;

}
