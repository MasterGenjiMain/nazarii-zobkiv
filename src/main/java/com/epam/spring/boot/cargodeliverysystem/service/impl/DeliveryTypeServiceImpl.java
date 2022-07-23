package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryTypeDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryTypeMapper;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryTypeRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

    private final DeliveryTypeRepository deliveryTypeRepository;
    private final DeliveryTypeMapper deliveryTypeMapper;

    @Override
    public List<DeliveryTypeDto> getAllDeliveryTypes() {
        log.info("getAllDeliveryTypes {}", "");
        return deliveryTypeRepository
                .getAllDeliveryTypes()
                .stream()
                .map(deliveryTypeMapper::mapDeliveryTypeToDeliveryTypeDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryTypeDto getDeliveryType(String typeName) {
        log.info("getDeliveryType by name {}", typeName);
        DeliveryType deliveryType = deliveryTypeRepository.getDeliveryTypeByName(typeName);
        return deliveryTypeMapper.mapDeliveryTypeToDeliveryTypeDto(deliveryType);
    }

    @Override
    public List<DeliveryTypeDto> getDeliveryTypesByLanguageId(long id) {
        log.info("getDeliveryTypesByLanguageId {}", id);
        List<DeliveryType> deliveryTypeList = deliveryTypeRepository.getDeliveryTypesByLanguageId(id);
        return deliveryTypeList.stream()
                .map(deliveryTypeMapper::mapDeliveryTypeToDeliveryTypeDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryTypeDto addDeliveryType(DeliveryTypeDto deliveryTypeDto) {
        log.info("addDeliveryType with name {}", deliveryTypeDto.getTypeName());
        DeliveryType deliveryType = deliveryTypeMapper.mapDeliveryTypeDtoToDeliveryType(deliveryTypeDto);
        deliveryType = deliveryTypeRepository.addDeliveryType(deliveryType);
        return deliveryTypeMapper.mapDeliveryTypeToDeliveryTypeDto(deliveryType);
    }

    @Override
    public DeliveryTypeDto updateDeliveryType(String typeName, DeliveryTypeDto deliveryTypeDto) {
        log.info("updateDeliveryOrder with name {}", typeName);
        DeliveryType deliveryType = deliveryTypeMapper.mapDeliveryTypeDtoToDeliveryType(deliveryTypeDto);
        deliveryType = deliveryTypeRepository.updateDeliveryType(typeName, deliveryType);
        return deliveryTypeMapper.mapDeliveryTypeToDeliveryTypeDto(deliveryType);
    }

    @Override
    public boolean deleteDeliveryType(String typeName) {
        log.info("deleteDeliveryOrder with name {}", typeName);
        return deliveryTypeRepository.deleteDeliveryType(typeName);
    }
}
