package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

public interface DeliveryCalculatorService {

    ReceiptDto calculate(ReceiptDto receiptDto);
}
