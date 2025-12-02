package org.action.api.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.action.base.request.BaseRequest;

/**
 * @author gzw
 * @description：
 * @since：2025/2/9 12:39
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest extends BaseRequest {

    private Long userId;
    private String realName;
    private String idCard;

}
