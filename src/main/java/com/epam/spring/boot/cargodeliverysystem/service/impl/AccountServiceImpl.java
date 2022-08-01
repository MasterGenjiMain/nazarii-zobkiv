package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final ReceiptStatusMapper receiptStatusMapper;

    @Override
    public List<ReceiptDto> giveAllUserReceipts(Long userId) {
        log.info("[AccountServiceImpl] showUserReceipts with userId {}", userId);
        return receiptRepository
                .findAllByUserId(userId)
                .stream()
                .map(receiptMapper::mapReceiptToReceiptDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto) {
        log.info("[AccountServiceImpl] changeReceiptStatus with id {}", receiptId);
        Receipt persistedReceipt = receiptRepository.findById(receiptId)
                .orElseThrow(EntityNotFoundException::new);
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(receiptStatusDto);
        persistedReceipt.setReceiptStatus(receiptStatus);
        Receipt storedReceipt = receiptRepository.save(persistedReceipt);
        log.info("Receipt with {} id successfully updated. New status {}", storedReceipt.getId(), storedReceipt.getReceiptStatus());
        return receiptMapper.mapReceiptToReceiptDto(persistedReceipt);
    }
}
