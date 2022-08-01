package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;

import java.util.List;

public interface AccountService {

    List<ReceiptDto> giveAllUserReceipts(Long userId);

    ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto);
}
