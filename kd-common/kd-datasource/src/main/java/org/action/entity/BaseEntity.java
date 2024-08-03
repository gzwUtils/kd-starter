package org.action.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:41
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private int id;

    /**
     * 创建时间
     */


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    /**
     * 逻辑删除
     */

    @TableField(fill = FieldFill.INSERT)
    private int delFlag;


    /**
     * 乐观锁版本号
     */

    @TableField(fill = FieldFill.INSERT)
    private int lockVersion;

}
