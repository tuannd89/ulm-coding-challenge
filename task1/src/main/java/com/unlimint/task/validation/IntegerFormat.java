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
@Constraint(validatedBy = { IntegerValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface IntegerFormat {
    String message()  default "Must be an positive integer";

    int min() default 1;

    int max() default Integer.MAX_VALUE;

    boolean checkRange() default true;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
