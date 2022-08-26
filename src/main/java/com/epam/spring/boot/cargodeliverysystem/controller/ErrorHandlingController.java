package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.exception.ServiceException;
import com.epam.spring.boot.cargodeliverysystem.model.Error;
import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: exception {}", ex.getMessage(), ex);
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(
                        err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleServiceException(ServiceException ex, HandlerMethod handlerMethod) {
        log.error("handleServiceException: message: {}, method: {}", ex.getMessage(),
                handlerMethod.getMethod(), ex);
        return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handlerException(ServiceException ex, HandlerMethod handlerMethod) {
        log.error("handlerException: message: {}, method: {}", ex.getMessage(),
                handlerMethod.getMethod(), ex);
        return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }

}