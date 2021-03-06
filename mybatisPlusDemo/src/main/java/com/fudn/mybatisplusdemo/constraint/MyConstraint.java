package com.fudn.mybatisplusdemo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fdn
 * @since 2021-11-10 22:05
 */
@Constraint(validatedBy = {MyConstraintValidator.class})
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConstraint {
    //只有一个参数时, 默认名字一般用value，这样在使用的时候 参数 value 可省略不写
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
