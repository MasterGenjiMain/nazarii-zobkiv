package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

import java.util.List;

public interface ReceiptService {

    List<ReceiptDto> getAllReceipts();

    ReceiptDto getReceipt(long id);

    ReceiptDto createReceipt(ReceiptDto receiptDto);

    ReceiptDto updateReceipt(long id, ReceiptDto receiptDto);

    boolean deleteReceipt (long id);
}
