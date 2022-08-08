package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

public interface DeliveryRequestService {

    ReceiptDto createNewDeliveryRequest(ReceiptDto receiptDto);
}
