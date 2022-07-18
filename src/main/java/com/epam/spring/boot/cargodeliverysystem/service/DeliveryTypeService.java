package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryTypeDto;

import java.util.List;

public interface DeliveryTypeService {

    List<DeliveryTypeDto> getAllDeliveryTypes();

    DeliveryTypeDto getDeliveryType(String typeName);

    List<DeliveryTypeDto> getDeliveryTypesByLanguageId(long id);

    DeliveryTypeDto addDeliveryType(DeliveryTypeDto deliveryTypeDto);

    DeliveryTypeDto updateDeliveryType(String typeName, DeliveryTypeDto deliveryTypeDto);

    boolean deleteDeliveryType(String typeName);
}
