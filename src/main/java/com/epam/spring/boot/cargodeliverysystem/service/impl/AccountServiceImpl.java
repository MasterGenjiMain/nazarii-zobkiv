package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.AccountService;
import com.epam.spring.boot.cargodeliverysystem.service.ChangeReceiptStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Long PAYED_STATUS = 3L;
    private static final Long CANCELED_STATUS = 8L;

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final ChangeReceiptStatusService changeReceiptStatusService;
    private final ReceiptStatusRepository receiptStatusRepository;
    private final ReceiptStatusMapper receiptStatusMapper;

    @Override
    public List<ReceiptDto> getAllUserReceipts(Long userId, int pageNum) {
        log.info("[AccountServiceImpl] showUserReceipts with userId {}", userId);

        Pageable pageable = PageRequest.of(pageNum, 3);

        return receiptRepository
                .findAllByUserId(userId, pageable)
                .stream()
                .map(receiptMapper::mapReceiptToReceiptDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReceiptDto pay(Long receiptId) {
        log.info("[ApproveServiceImpl] approve with id {}", receiptId);
        ReceiptStatus payedStatus = receiptStatusRepository.findById(PAYED_STATUS)
                .orElseThrow(EntityNotFoundException::new);
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(payedStatus));
    }

    @Override
    public ReceiptDto cansel(Long receiptId) {
        log.info("[ApproveServiceImpl] cansel with id {}", receiptId);
        ReceiptStatus canceledStatus = receiptStatusRepository.findById(CANCELED_STATUS)
                .orElseThrow(EntityNotFoundException::new);
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(canceledStatus));
    }

    public ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto) {
        log.info("[AccountServiceImpl] changeReceiptStatus with id {}", receiptId);
        return changeReceiptStatusService.changeReceiptStatus(receiptId, receiptStatusDto, receiptRepository);
    }
}
