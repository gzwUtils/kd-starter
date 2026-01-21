package org.action.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.action.base.validator.IsMobile;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 22:49
 */

@Data
public class RegisterParam {

    /**
     * 手机号
     */
    @IsMobile
    private String telephone;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    /**
     * 邀请码
     */
    private String inviteCode;

}
