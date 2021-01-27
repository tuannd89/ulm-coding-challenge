package com.unlimint.task.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { NumberValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface NumberFormat {
    String message() default "Must be a number is greater than or equals to 0";

    long min() default 0;

    long max() default Long.MAX_VALUE;

    boolean checkRange() default true;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
