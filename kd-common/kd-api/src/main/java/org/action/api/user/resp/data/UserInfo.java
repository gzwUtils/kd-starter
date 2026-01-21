package org.action.api.user.resp.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.api.user.enums.UserRole;
import org.action.api.user.enums.UserStateEnum;

import java.util.Date;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:35
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfo extends BasicUserInfo {

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 状态
     *
     * @see UserStateEnum
     */
    private String state;


    /**
     * 实名认证
     */
    private Boolean certification;

    /**
     * 用户角色
     */
    private UserRole userRole;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 注册时间
     */
    private Date createTime;

}
