package org.action.notice.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.action.entity.BaseEntity;

/**
 * @author gzw
 * @description：
 * @since：2023/5/24 14:42
 */

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class TemplateInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 模版id
     */
    private String tempId;


    /**
     * 模板名称
     */
    private String tempName;


    /**
     * 模版状态
     */
    private Integer tempStatus;


    /**
     * 发送渠道
     */
    private Integer sendChannel;


    /**
     * 模板类型
     */

    private Integer templateType;

    /**
     * 模版内容  {$var} 为占位符
     */
    private String tempContent;


    private String creator;


    private String updater;


}
