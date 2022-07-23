package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;

import java.util.List;

public interface DeliveryTypeRepository {

    List<DeliveryType> getAllDeliveryTypes();

    DeliveryType getDeliveryTypeByName(String typeName);

    List<DeliveryType> getDeliveryTypesByLanguageId(long id);

    DeliveryType addDeliveryType(DeliveryType deliveryType);

    DeliveryType updateDeliveryType(String typeName, DeliveryType deliveryType);

    boolean deleteDeliveryType(String typeName);
}
