package com.fudn.mybatisplusdemo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author fdn
 * @since 2021-11-10 22:04
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Integer a = null;
        if (value instanceof Integer) {
            a = (Integer) value;
        }
        System.out.println(value);
        return a == 5;
    }

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }
}
