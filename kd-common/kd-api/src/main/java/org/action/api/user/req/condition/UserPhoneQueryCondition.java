package org.action.api.user.req.condition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneQueryCondition implements UserQueryCondition {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    private String telephone;

}
