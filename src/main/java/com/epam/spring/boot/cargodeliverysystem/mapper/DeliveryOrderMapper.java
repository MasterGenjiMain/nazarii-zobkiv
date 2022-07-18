package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {

    DeliveryOrderDto mapDeliveryOrderToDeliveryOrderDto (DeliveryOrder deliveryOrder);

    DeliveryOrder mapDeliveryOrderDtoToDeliveryOrder(DeliveryOrderDto deliveryOrderDto);
}
