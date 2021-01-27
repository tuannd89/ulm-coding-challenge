package com.unlimint.task.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class NumberValidator implements ConstraintValidator<NumberFormat, String> {

    private long min;

    private long max;

    private boolean checkRange;

    @Override
    public void initialize(NumberFormat numberFormat) {
        min = numberFormat.min();
        max = numberFormat.max();
        checkRange = numberFormat.checkRange();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        if (StringUtils.isNotEmpty(value)) {
            try {
                BigDecimal dValue = new BigDecimal(value);
                return !checkRange || (dValue.compareTo(BigDecimal.valueOf(min)) >= 0
                    && dValue.compareTo(BigDecimal.valueOf(max)) <= 0);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
