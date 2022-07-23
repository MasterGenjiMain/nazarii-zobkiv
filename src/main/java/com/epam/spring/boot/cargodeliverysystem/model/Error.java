package com.epam.spring.boot.cargodeliverysystem.model;

import com.epam.spring.boot.cargodeliverysystem.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorType errorType;
    private LocalDateTime errorTime;

}
