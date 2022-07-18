package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;

    @Override
    public List<DeliveryOrderDto> getAllDeliveryOrders() {
        log.info("getAllDeliveryOrders {}", "");
        return deliveryOrderRepository
                .getAllDeliveryOrders()
                .stream()
                .map(this::mapDeliveryOrderToDeliveryOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryOrderDto getDeliveryOrder(long id) {
        log.info("getDeliveryOrder by id {}", id);
        DeliveryOrder deliveryOrder = deliveryOrderRepository.getDeliveryOrder(id);
        return mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        log.info("createDeliveryOrder with id {}", deliveryOrderDto.getId());
        DeliveryOrder deliveryOrder = mapDeliveryOrderDtoToDeliveryOrder(deliveryOrderDto);
        deliveryOrder = deliveryOrderRepository.createDeliveryOrder(deliveryOrder);
        return mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto updateDeliveryOrder(long id, DeliveryOrderDto deliveryOrderDto) {
        log.info("updateUser with id {}", id);
        DeliveryOrder deliveryOrder = mapDeliveryOrderDtoToDeliveryOrder(deliveryOrderDto);
        deliveryOrder = deliveryOrderRepository.updateDeliveryOrder(id, deliveryOrder);
        return mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    @Override
    public boolean deleteDeliveryOrder(long id) {
        log.info("deleteDeliveryOrder with id {}", id);
        return deliveryOrderRepository.deleteDeliveryOrder(id);
    }

    private DeliveryOrderDto mapDeliveryOrderToDeliveryOrderDto(DeliveryOrder deliveryOrder) {
        return DeliveryOrderDto.builder()
                .id(deliveryOrder.getId())
                .locationFromId(deliveryOrder.getLocationFromId())
                .locationToId(deliveryOrder.getLocationToId())
                .cargoName(deliveryOrder.getCargoName())
                .cargoDescription(deliveryOrder.getCargoDescription())
                .address(deliveryOrder.getAddress())
                .deliveryTypeId(deliveryOrder.getDeliveryTypeId())
                .weight(deliveryOrder.getWeight())
                .volume(deliveryOrder.getVolume())
                .receivingDate(deliveryOrder.getReceivingDate())
                .tariffId(deliveryOrder.getTariffId())
                .build();
    }

    private DeliveryOrder mapDeliveryOrderDtoToDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        return DeliveryOrder.builder()
                .id(deliveryOrderDto.getId())
                .locationFromId(deliveryOrderDto.getLocationFromId())
                .locationToId(deliveryOrderDto.getLocationToId())
                .cargoName(deliveryOrderDto.getCargoName())
                .cargoDescription(deliveryOrderDto.getCargoDescription())
                .address(deliveryOrderDto.getAddress())
                .deliveryTypeId(deliveryOrderDto.getDeliveryTypeId())
                .weight(deliveryOrderDto.getWeight())
                .volume(deliveryOrderDto.getVolume())
                .receivingDate(deliveryOrderDto.getReceivingDate())
                .tariffId(deliveryOrderDto.getTariffId())
                .build();
    }
}
