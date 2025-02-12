package org.action.vo;

import cn.dev33.satoken.stp.StpUtil;
import java.io.Serializable;
import lombok.Data;
import org.action.user.resp.data.UserInfo;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:12
 */

@Data
public class LoginVO implements Serializable {

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


    public LoginVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId().toString();
        this.token = StpUtil.getTokenValue();
        this.tokenExpiration = StpUtil.getTokenSessionTimeout();
    }

}
