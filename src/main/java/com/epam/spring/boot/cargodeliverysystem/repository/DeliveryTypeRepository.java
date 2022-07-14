package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;

public interface DeliveryTypeRepository {

    DeliveryType getDeliveryType(String typeName);

    DeliveryType addDeliveryType(DeliveryType deliveryType);

    DeliveryType updateDeliveryType(String typeName, DeliveryType deliveryType);

    boolean deleteDeliveryType(String typeName);
}
