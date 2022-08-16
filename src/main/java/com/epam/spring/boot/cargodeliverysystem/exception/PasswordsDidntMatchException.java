package com.epam.spring.boot.cargodeliverysystem.exception;

import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;

public class PasswordsDidntMatchException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Password and repeat password didn't match";

    public PasswordsDidntMatchException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
