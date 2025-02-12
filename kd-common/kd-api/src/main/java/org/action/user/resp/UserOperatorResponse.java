package org.action.user.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.response.BaseResponse;
import org.action.user.resp.data.UserInfo;

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
