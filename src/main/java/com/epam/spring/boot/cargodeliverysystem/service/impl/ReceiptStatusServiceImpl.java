package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptStatusServiceImpl implements ReceiptStatusService {

    private final ReceiptStatusRepository receiptStatusRepository;
    private final ReceiptStatusMapper receiptStatusMapper;

    @Override
    public ReceiptStatusDto getReceiptStatus(String statusName) {
        log.info("getReceiptStatus by id {}", statusName);
        ReceiptStatus receiptStatus = receiptStatusRepository.getReceiptStatus(statusName);
        return receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(receiptStatus);
    }

    @Override
    public ReceiptStatusDto addReceiptStatus(ReceiptStatusDto receiptStatusDto) {
        log.info("addReceiptStatus with name {}", receiptStatusDto.getStatusName());
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(receiptStatusDto);
        receiptStatus = receiptStatusRepository.addReceiptStatus(receiptStatus);
        return receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(receiptStatus);
    }

    @Override
    public ReceiptStatusDto updateReceiptStatus(String statusName, ReceiptStatusDto receiptStatusDto) {
        log.info("updateReceiptStatus with name {}", statusName);
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(receiptStatusDto);

        ReceiptStatus oldReceiptStatus = receiptStatusRepository.getReceiptStatus(statusName);
        receiptStatus.setId(oldReceiptStatus.getId());

        receiptStatus = receiptStatusRepository.updateReceiptStatus(statusName, receiptStatus);
        return receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(receiptStatus);
    }

    @Override
    public boolean deleteReceiptStatus(String statusName) {
        log.info("deleteReceiptStatus with name {}", statusName);
        return receiptStatusRepository.deleteReceiptStatus(statusName);
    }
}
