package org.action.user.enums;

import lombok.Getter;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:25
 */
@Getter
public enum UserType {

    /**
     * 用户
     */
    CUSTOMER("用户"),

    /**
     * 平台
     */
    PLATFORM("平台");

    private final String desc;

    UserType(String desc) {
        this.desc = desc;
    }


}
