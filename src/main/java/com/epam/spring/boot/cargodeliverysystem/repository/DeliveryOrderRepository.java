package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderRepository {

    List<DeliveryOrder> getAllDeliveryOrders();

    DeliveryOrder getDeliveryOrder(long id);

    DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder);

    DeliveryOrder updateDeliveryOrder(long id, DeliveryOrder deliveryOrder);

    boolean deleteDeliveryOrder(long id);
}
