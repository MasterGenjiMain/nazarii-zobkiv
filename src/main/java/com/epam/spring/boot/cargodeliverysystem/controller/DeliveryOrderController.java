package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliveryOrders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public DeliveryOrderDto getDeliveryOrder(@PathVariable long id) {
        log.info("[Controller] getDeliveryOrder by id {} ", id);
        return deliveryOrderService.getDeliveryOrder(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<DeliveryOrderDto> getAllDeliveryOrders(){
        log.info("[Controller] getAllDeliveryOrders {} ", "");
        return deliveryOrderService.getAllDeliveryOrders();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DeliveryOrderDto createDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrderDto) {
        log.info("[Controller] createDeliveryOrder {} ", deliveryOrderDto);
        return deliveryOrderService.createDeliveryOrder(deliveryOrderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public DeliveryOrderDto updateDeliveryOrder(@PathVariable long id, @RequestBody DeliveryOrderDto deliveryOrderDto) {
        log.info("[Controller] updateDeliveryOrder by id {} ", id);
        return deliveryOrderService.updateDeliveryOrder(id, deliveryOrderDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public boolean deleteDeliveryOrder(@PathVariable long id) {
        log.info("[Controller] deleteDeliveryOrder by id {} ", id);
        return deliveryOrderService.deleteDeliveryOrder(id);
    }
}
