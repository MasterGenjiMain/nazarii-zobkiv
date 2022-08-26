package com.epam.spring.boot.cargodeliverysystem.exception;

import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ServiceException extends RuntimeException {

    protected ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
