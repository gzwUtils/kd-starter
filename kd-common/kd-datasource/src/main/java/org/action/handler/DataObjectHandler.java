package org.action.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 22:12
 */
public class DataObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByNameIfNull("createTime", new Date(), metaObject);
        this.setFieldValByNameIfNull("updateTime", new Date(), metaObject);
        this.setFieldValByName("delFlag", 0, metaObject);
        this.setFieldValByName("lockVersion", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByNameIfNull("updateTime", new Date(), metaObject);
    }

    /**
     * 当没有值的时候再设置属性，如果有值则不设置
     * @param fieldName  fieldName
     * @param fieldVal  val
     * @param metaObject ob
     */
    private void setFieldValByNameIfNull(String fieldName, Object fieldVal, MetaObject metaObject) {
        if (metaObject.getValue(fieldName) == null) {
            this.setFieldValByName(fieldName, fieldVal, metaObject);
        }
    }
}
