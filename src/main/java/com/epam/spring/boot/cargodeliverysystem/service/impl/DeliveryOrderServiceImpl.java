package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
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
    private final DeliveryOrderMapper deliveryOrderMapper;

    @Override
    public List<DeliveryOrderDto> getAllDeliveryOrders() {
        log.info("getAllDeliveryOrders {}", "");
        return deliveryOrderRepository
                .getAllDeliveryOrders()
                .stream()
                .map(deliveryOrderMapper::mapDeliveryOrderToDeliveryOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryOrderDto getDeliveryOrder(long id) {
        log.info("getDeliveryOrder by id {}", id);
        DeliveryOrder deliveryOrder = deliveryOrderRepository.getDeliveryOrder(id);
        return deliveryOrderMapper.mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        log.info("createDeliveryOrder with id {}", deliveryOrderDto.getId());
        DeliveryOrder deliveryOrder = deliveryOrderMapper.mapDeliveryOrderDtoToDeliveryOrder(deliveryOrderDto);
        deliveryOrder = deliveryOrderRepository.createDeliveryOrder(deliveryOrder);
        return deliveryOrderMapper.mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto updateDeliveryOrder(long id, DeliveryOrderDto deliveryOrderDto) {
        log.info("updateUser with id {}", id);
        DeliveryOrder deliveryOrder = deliveryOrderMapper.mapDeliveryOrderDtoToDeliveryOrder(deliveryOrderDto);
        deliveryOrder = deliveryOrderRepository.updateDeliveryOrder(id, deliveryOrder);
        return deliveryOrderMapper.mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    @Override
    public boolean deleteDeliveryOrder(long id) {
        log.info("deleteDeliveryOrder with id {}", id);
        return deliveryOrderRepository.deleteDeliveryOrder(id);
    }
}
