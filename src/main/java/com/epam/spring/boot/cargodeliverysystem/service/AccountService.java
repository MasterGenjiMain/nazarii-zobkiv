package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;

import java.util.List;

public interface AccountService {

    List<ReceiptDto> getAllUserReceipts(Long userId, int pageNum);

    ReceiptDto pay(Long receiptId);

    ReceiptDto cansel(Long receiptId);
}
