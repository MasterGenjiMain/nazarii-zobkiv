package com.epam.spring.boot.cargodeliverysystem.customvalidator;

import com.epam.spring.boot.cargodeliverysystem.customvalidator.annotation.PasswordConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    private int passwordLength;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        passwordLength = constraintAnnotation.passwordLength();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.length() >= passwordLength;
    }
}
