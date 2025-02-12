package org.action.user.req;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.action.request.BaseRequest;

/**
 * @author gzw
 * @description：
 * @since：2025/2/9 12:38
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyRequest extends BaseRequest {

    @NotNull(message = "userId不能为空")
    private Long userId;

    private String nickName;

    private String password;

    private String profilePhotoUrl;

    private String telephone;


}
