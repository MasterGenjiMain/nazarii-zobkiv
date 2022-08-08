package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliveryOrderReport")
@RequiredArgsConstructor
public class DeliveryOrderReportController {

    private final DeliveryOrderReportService deliveryOrderReportService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<DeliveryOrderDto> getAllDeliveryOrders(@RequestParam(defaultValue = "0") int pageNum,
                                                       @RequestParam(required = false) String sortBy) {
        log.info("[DeliveryOrderReportController] getAllDeliveryOrders");
        return deliveryOrderReportService.getAllDeliveryOrders(pageNum, sortBy);
    }

}
