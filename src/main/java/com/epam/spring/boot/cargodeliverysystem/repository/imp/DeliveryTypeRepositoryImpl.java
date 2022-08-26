package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DeliveryTypeRepositoryImpl implements DeliveryTypeRepository {

    private final List<DeliveryType> deliveryTypeList = new ArrayList<>();

    @Override
    public List<DeliveryType> getAllDeliveryTypes() {
        log.info("[Repository] getAllDeliveryTypes {} ", deliveryTypeList);
        return deliveryTypeList;
    }

    @Override
    public DeliveryType getDeliveryTypeByName(String typeName) {
        log.info("[Repository] getDeliveryType by name {} ", typeName);
        return deliveryTypeList.stream()
                .filter(type -> type.getTypeName().equals(typeName))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<DeliveryType> getDeliveryTypesByLanguageId(long id) {
        log.info("[Repository] getDeliveryTypeByLanguageId by id {} ", id);
        return deliveryTypeList.stream()
                .filter(type -> type.getLanguageId() == id)
                .toList();
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
            throw new EntityNotFoundException();
        }
        return deliveryOrder;
    }

    @Override
    public boolean deleteDeliveryType(String typeName) {
        log.info("[Repository] deleteDeliveryType by name {} ", typeName);
        return deliveryTypeList.removeIf(type -> type.getTypeName().equals(typeName));
    }
}
