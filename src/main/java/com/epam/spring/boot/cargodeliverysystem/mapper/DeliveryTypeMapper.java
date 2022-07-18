package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryTypeDto;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryTypeMapper {

    DeliveryTypeDto mapDeliveryTypeToDeliveryTypeDto(DeliveryType deliveryType);

    DeliveryType mapDeliveryTypeDtoToDeliveryType(DeliveryTypeDto deliveryTypeDto);
}
