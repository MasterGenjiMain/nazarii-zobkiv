package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DeliveryTypeRepositoryImpl implements DeliveryTypeRepository {

    private final List<DeliveryType> deliveryTypeList = new ArrayList<>();

    @Override
    public DeliveryType getDeliveryType(String typeName) {
        log.info("[Repository] getDeliveryType by name {} ", typeName);
        return deliveryTypeList.stream()
                .filter(type -> type.getTypeName().equals(typeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Delivery Type is not found!"));
    }

    @Override
    public DeliveryType addDeliveryType(DeliveryType deliveryType) {
        log.info("[Repository] addDeliveryType {} ", deliveryType);
        deliveryTypeList.add(deliveryType);
        return deliveryType;
    }

    @Override
    public DeliveryType updateDeliveryType(String typeName, DeliveryType deliveryOrder) {
        log.info("[Repository] updateDeliveryType by name {} ", typeName);
        boolean isDeleted = deliveryTypeList.removeIf(type -> type.getTypeName().equals(typeName));
        if(isDeleted) {
            deliveryTypeList.add(deliveryOrder);
        } else {
            throw new RuntimeException("Delivery Type is not found!");
        }
        return deliveryOrder;
    }

    @Override
    public boolean deleteDeliveryType(String typeName) {
        log.info("[Repository] deleteDeliveryType by name {} ", typeName);
        return deliveryTypeList.removeIf(type -> type.getTypeName().equals(typeName));
    }
}
