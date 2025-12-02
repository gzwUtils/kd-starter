package org.action.api.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.action.api.user.req.condition.UserIdQueryCondition;
import org.action.api.user.req.condition.UserPhoneAndPasswordQueryCondition;
import org.action.api.user.req.condition.UserPhoneQueryCondition;
import org.action.api.user.req.condition.UserQueryCondition;
import org.action.base.request.BaseRequest;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest extends BaseRequest {

    private UserQueryCondition userQueryCondition;

    public UserQueryRequest(Long userId) {
        UserIdQueryCondition userIdQueryCondition = new UserIdQueryCondition();
        userIdQueryCondition.setUserId(userId);
        this.userQueryCondition = userIdQueryCondition;
    }

    public UserQueryRequest(String telephone) {
        UserPhoneQueryCondition userPhoneQueryCondition = new UserPhoneQueryCondition();
        userPhoneQueryCondition.setTelephone(telephone);
        this.userQueryCondition = userPhoneQueryCondition;
    }

    public UserQueryRequest(String telephone, String password) {
        UserPhoneAndPasswordQueryCondition userPhoneAndPasswordQueryCondition = new UserPhoneAndPasswordQueryCondition();
        userPhoneAndPasswordQueryCondition.setTelephone(telephone);
        userPhoneAndPasswordQueryCondition.setPassword(password);
        this.userQueryCondition = userPhoneAndPasswordQueryCondition;
    }


}
