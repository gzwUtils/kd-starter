package org.action.api.user.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.api.user.resp.data.UserInfo;
import org.action.base.response.BaseResponse;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:34
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserOperatorResponse extends BaseResponse {

    private UserInfo user;

}
