package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryOrderReportServiceImpl implements DeliveryOrderReportService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryOrderMapper deliveryOrderMapper;

    @Override
    public List<DeliveryOrderDto> showAllDeliveryOrders() {
        log.info("[DeliveryOrderReportServiceImpl] showAllDeliveryOrders");
        return deliveryOrderRepository.findAll()
                .stream()
                .map(deliveryOrderMapper::mapDeliveryOrderToDeliveryOrderDto)
                .collect(Collectors.toList());
    }
}
