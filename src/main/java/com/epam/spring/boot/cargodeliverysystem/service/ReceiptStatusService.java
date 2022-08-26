package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;

public interface ReceiptStatusService {

    ReceiptStatusDto getReceiptStatus(String statusName);

    ReceiptStatusDto addReceiptStatus(ReceiptStatusDto receiptStatusDto);

    ReceiptStatusDto updateReceiptStatus(String statusName, ReceiptStatusDto receiptStatusDto);

    boolean deleteReceiptStatus(String statusName);
}
