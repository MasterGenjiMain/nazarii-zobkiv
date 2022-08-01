package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/deliveryRequestService")
@RequiredArgsConstructor
public class DeliveryRequestController {

    private final DeliveryRequestService deliveryRequestService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReceiptDto createNewDeliveryRequest(@RequestBody @Validated(OnUpdate.class) DeliveryOrderDto deliveryOrderDto, ReceiptDto receiptDto) {
        log.info("[DeliveryRequestController] createNewDeliveryRequest");
        return deliveryRequestService.createNewDeliveryRequest(deliveryOrderDto, receiptDto);
    }
}
