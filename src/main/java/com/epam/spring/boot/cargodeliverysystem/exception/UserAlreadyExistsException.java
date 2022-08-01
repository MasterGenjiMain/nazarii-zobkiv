package com.epam.spring.boot.cargodeliverysystem.exception;

import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;

public class UserAlreadyExistsException extends ServiceException{

    private static final String DEFAULT_MESSAGE = "Email already registered";

    public UserAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
