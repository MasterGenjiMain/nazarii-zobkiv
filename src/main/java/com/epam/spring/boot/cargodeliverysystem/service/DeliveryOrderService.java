package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;

public interface DeliveryOrderService {

    DeliveryOrderDto getDeliveryOrder(long id);

    DeliveryOrderDto createDeliveryOrder(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto updateDeliveryOrder(long id, DeliveryOrderDto deliveryOrderDto);

    boolean deleteDeliveryOrder(long id);
}
