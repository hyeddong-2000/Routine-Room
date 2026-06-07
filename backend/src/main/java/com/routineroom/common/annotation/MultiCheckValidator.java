package com.routineroom.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class MultiCheckValidator implements ConstraintValidator<MultiCheck, String> {

    private static final Pattern RRN_PATTERN =
            Pattern.compile("\\d{6}-[1-4]\\d{6}");

    private static final Pattern MORPHEME_PATTERN =
            Pattern.compile("[가-힣]{2,}\\s?씨\\s?[가-힣]");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (RRN_PATTERN.matcher(value).find()) {
            return false;
        }
        if (MORPHEME_PATTERN.matcher(value).find()) {
            return false;
        }
        return true;
    }
}
