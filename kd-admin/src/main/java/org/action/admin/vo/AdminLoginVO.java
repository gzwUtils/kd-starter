package org.action.admin.vo;

import cn.dev33.satoken.stp.StpUtil;
import lombok.*;
import org.action.api.user.resp.data.UserInfo;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录VO
 */
@Data
@NoArgsConstructor
@ToString
public class AdminLoginVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户标识，如用户ID
     */
    private String userId;
    /**
     * 访问令牌
     */
    private String token;

    /**
     * 令牌过期时间
     */
    private Long tokenExpiration;


    public AdminLoginVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId().toString();
        this.token = StpUtil.getTokenValue();
        this.tokenExpiration = StpUtil.getTokenSessionTimeout();
    }
}
