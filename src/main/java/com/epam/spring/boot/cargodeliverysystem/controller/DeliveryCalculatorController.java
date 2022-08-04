package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/deliveryCalculator")
@RequiredArgsConstructor
public class DeliveryCalculatorController {

    private final DeliveryCalculatorService deliveryCalculatorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ReceiptDto calculatePrice(@RequestBody @Validated(OnCreate.class)ReceiptDto receiptDto) {
        log.info("[DeliveryCalculatorController] calculatePrice");
        return deliveryCalculatorService.calculate(receiptDto);
    }
}
