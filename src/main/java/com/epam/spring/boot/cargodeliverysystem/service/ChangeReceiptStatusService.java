package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;

public interface ChangeReceiptStatusService {

    ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto, ReceiptRepository receiptRepository);
}
