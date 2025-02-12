package org.action.user.req.condition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneAndPasswordQueryCondition implements UserQueryCondition {

    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    private String telephone;

    /**
     * 密码
     */
    private String password;

}
