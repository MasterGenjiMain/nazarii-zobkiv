package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

public interface ReceiptService {

    ReceiptDto getReceipt(long id);

    ReceiptDto createReceipt(ReceiptDto receiptDto);

    ReceiptDto updateReceipt(long id, ReceiptDto receiptDto);

    boolean deleteReceipt (long id);
}
