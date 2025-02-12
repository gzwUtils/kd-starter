package org.action.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author gzw
 * @description：
 * @since：2025/2/8 23:06
 */
@Constraint(validatedBy = MobileValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsMobile {

    String message() default "手机号格式不正确"; // 默认错误信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
