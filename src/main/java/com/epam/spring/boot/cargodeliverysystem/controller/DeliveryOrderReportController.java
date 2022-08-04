package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliveryOrderReport")
@RequiredArgsConstructor
public class DeliveryOrderReportController {

    private final DeliveryOrderReportService deliveryOrderReportService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<DeliveryOrderDto> getAllDeliveryOrders() {
        log.info("[DeliveryOrderReportController] getAllDeliveryOrders");
        return deliveryOrderReportService.giveAllDeliveryOrders();
    }

}
