package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

public interface ReceiptManagerService {

    ReceiptDto nextStatus(Long receiptId);

    ReceiptDto approve(Long receiptId);

    ReceiptDto cancel(Long receiptId);
}
