package org.action.base.validator;

import cn.hutool.core.lang.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/**
 * @author gzw
 * @description： 手机号校验器
 * @since：2025/2/8 23:08
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Validator.isMobile(value);
    }
}
