package org.action.user.common.entity;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.datasource.entity.BaseEntity;

/**
 * @author gzw
 * @description：
 * @since：2025/2/12 21:30
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserOperateStream extends BaseEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作参数
     */
    private String param;

    /**
     * 扩展字段
     */
    private String extendInfo;


}
