package org.action.admin.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 管理员注册参数
 */
@Data
public class AdminRegisterParam {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String telephone;

    /**
     * 验证码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
