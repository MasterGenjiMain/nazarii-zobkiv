package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.service.ChangeReceiptStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeReceiptStatusServiceImpl implements ChangeReceiptStatusService {

    private final ReceiptStatusMapper receiptStatusMapper;
    private final ReceiptMapper receiptMapper;

    @Override
    public ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto, ReceiptRepository receiptRepository) {
        log.info("[ChangeReceiptStatusServiceImpl] changeReceiptStatus with id {}", receiptId);
        Receipt persistedReceipt = receiptRepository.findById(receiptId)
                .orElseThrow(EntityNotFoundException::new);
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(receiptStatusDto);
        persistedReceipt.setReceiptStatus(receiptStatus);
        Receipt storedReceipt = receiptRepository.save(persistedReceipt);
        log.info("Receipt with {} id successfully updated. New status {}", storedReceipt.getId(), storedReceipt.getReceiptStatus());
        return receiptMapper.mapReceiptToReceiptDto(persistedReceipt);
    }
}
