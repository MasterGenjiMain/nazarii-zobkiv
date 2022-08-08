package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<DeliveryOrderDto> getAllDeliveryOrders(int pageNum) {
        log.info("[DeliveryOrderReportServiceImpl] showAllDeliveryOrders");

        Pageable pageable = PageRequest.of(pageNum, 3);

        return deliveryOrderRepository.findAll(pageable)
                .stream()
                .map(deliveryOrderMapper::mapDeliveryOrderToDeliveryOrderDto)
                .collect(Collectors.toList());
    }
}
