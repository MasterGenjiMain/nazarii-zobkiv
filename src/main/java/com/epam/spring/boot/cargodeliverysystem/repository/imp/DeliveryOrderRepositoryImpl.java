package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DeliveryOrderRepositoryImpl implements DeliveryOrderRepository {

    private final List<DeliveryOrder> deliveryOrderList = new ArrayList<>();

    @Override
    public DeliveryOrder getDeliveryOrder(long id) {
        log.info("[Repository] getDeliveryOrder by id {} ", id);
        return deliveryOrderList.stream()
                .filter(ord -> ord.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Delivery Order is not found!"));
    }

    @Override
    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) {
        log.info("[Repository] createDeliveryOrder {} ", deliveryOrder);
        deliveryOrderList.add(deliveryOrder);
        return deliveryOrder;
    }

    @Override
    public DeliveryOrder updateDeliveryOrder(long id, DeliveryOrder deliveryOrder) {
        log.info("[Repository] updateDeliveryOrder by id {} ", id);
        boolean isDeleted = deliveryOrderList.removeIf(c -> c.getId() == id);
        if(isDeleted) {
            deliveryOrderList.add(deliveryOrder);
        } else {
            throw new RuntimeException("Delivery Order is not found!");
        }
        return deliveryOrder;
    }

    @Override
    public boolean deleteDeliveryOrder(long id) {
        log.info("[Repository] deleteDeliveryOrder by id {} ", id);
        return deliveryOrderList.removeIf(ord -> ord.getId() == id);
    }
}
