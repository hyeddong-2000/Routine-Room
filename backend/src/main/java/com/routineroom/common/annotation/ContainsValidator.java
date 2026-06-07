package com.routineroom.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ContainsValidator implements ConstraintValidator<Contains, String> {

    private Set<String> acceptedValues;

    @Override
    public void initialize(Contains constraintAnnotation) {
        acceptedValues = Set.of(constraintAnnotation.acceptedValues());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value);
    }
}
