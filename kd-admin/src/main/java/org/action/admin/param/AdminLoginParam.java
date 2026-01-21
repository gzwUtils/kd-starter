package org.action.admin.param;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminLoginParam extends AdminRegisterParam {


    /**
     * 记住我
     */
    private Boolean rememberMe;
}
