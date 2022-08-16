package com.epam.spring.boot.cargodeliverysystem.exception;

import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;

public class DeliveryAlreadyDeliveredException extends ServiceException{

    private static final String DEFAULT_MESSAGE = "Delivery already delivered";

    public DeliveryAlreadyDeliveredException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
