package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderService {

    List<DeliveryOrderDto> getAllDeliveryOrders();

    DeliveryOrderDto getDeliveryOrder(long id);

    DeliveryOrderDto createDeliveryOrder(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto updateDeliveryOrder(long id, DeliveryOrderDto deliveryOrderDto);

    boolean deleteDeliveryOrder(long id);
}
