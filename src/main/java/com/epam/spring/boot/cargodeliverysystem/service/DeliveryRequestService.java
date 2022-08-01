package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

public interface DeliveryRequestService {

    ReceiptDto createNewDeliveryRequest (DeliveryOrderDto deliveryOrderDto, ReceiptDto receiptDto);
}
