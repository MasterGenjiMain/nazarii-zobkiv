package com.epam.spring.boot.cargodeliverysystem.exception;

import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;

public class EntityNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Entity not found";

    public EntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
