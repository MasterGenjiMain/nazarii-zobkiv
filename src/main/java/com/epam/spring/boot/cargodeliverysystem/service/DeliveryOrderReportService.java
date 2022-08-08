package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderReportService {

    List<DeliveryOrderDto> getAllDeliveryOrders(int pageNum);
}
