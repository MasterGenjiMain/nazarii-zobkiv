package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryTypeDto;

public interface DeliveryTypeService {

    DeliveryTypeDto getDeliveryType(String typeName);

    DeliveryTypeDto addDeliveryType(DeliveryTypeDto deliveryTypeDto);

    DeliveryTypeDto updateDeliveryType(String typeName, DeliveryTypeDto deliveryTypeDto);

    boolean deleteDeliveryType(String typeName);
}
