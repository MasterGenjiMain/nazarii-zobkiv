package com.epam.spring.boot.cargodeliverysystem.customvalidator.annotation;

import com.epam.spring.boot.cargodeliverysystem.customvalidator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    int passwordLength() default 6;

    String message() default "Password length isn't enough. Make it longer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
