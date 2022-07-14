package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;

public interface DeliveryOrderRepository {

    DeliveryOrder getDeliveryOrder(long id);

    DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder);

    DeliveryOrder updateDeliveryOrder(long id, DeliveryOrder deliveryOrder);

    boolean deleteDeliveryOrder(long id);
}
