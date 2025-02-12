package org.action.user.resp.data;

import java.io.Serializable;
import lombok.Data;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 21:35
 */

@Data
public class BasicUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String profilePhotoUrl;

}
