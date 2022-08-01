package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.ApproveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApproveServiceImpl implements ApproveService {

    private static final Long CLOSED_STATUS = 7L;

    private final ReceiptRepository receiptRepository;
    private final ReceiptStatusRepository receiptStatusRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final ReceiptMapper receiptMapper;
    private final ReceiptStatusMapper receiptStatusMapper;

    @Override
    public ReceiptDto nextStatus(Long receiptId) {
        log.info("[ApproveServiceImpl] nextStatus with id {}", receiptId);
        Receipt persistedReceipt = receiptRepository.findById(receiptId)
                .orElseThrow(EntityNotFoundException::new);
        Long currentStatusId = persistedReceipt.getReceiptStatus().getId();
        ReceiptStatus newReceiptStatus = null;
        if (currentStatusId < CLOSED_STATUS - 1) {
            log.debug("Current receipt with id {} not closed", receiptId);
            newReceiptStatus = receiptStatusRepository.findById(++currentStatusId)
                    .orElseThrow(EntityNotFoundException::new);
        } else if (currentStatusId == CLOSED_STATUS - 1) {
            log.debug("Current receipt with id {} pre closed", receiptId);
            DeliveryOrder persistedDeliveryOrder = persistedReceipt.getDeliveryOrder();
            persistedDeliveryOrder.setReceivingDate(new Timestamp(System.currentTimeMillis()));
            deliveryOrderRepository.save(persistedDeliveryOrder);
        }
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(newReceiptStatus));
    }

    @Override
    public ReceiptDto approve(Long receiptId) {
        log.info("[ApproveServiceImpl] approve with id {}", receiptId);
        ReceiptStatus approveStatus = receiptStatusRepository.findById(2L)
                .orElseThrow(EntityNotFoundException::new);
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(approveStatus));
    }

    @Override
    public ReceiptDto cansel(Long receiptId) {
        log.info("[ApproveServiceImpl] cansel with id {}", receiptId);
        ReceiptStatus canselStatus = receiptStatusRepository.findById(8L)
                .orElseThrow(EntityNotFoundException::new);
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(canselStatus));
    }

    public ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto) {
        log.info("[ApproveServiceImpl] changeReceiptStatus with id {}", receiptId);
        Receipt persistedReceipt = receiptRepository.findById(receiptId)
                .orElseThrow(EntityNotFoundException::new);
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(receiptStatusDto);
        persistedReceipt.setReceiptStatus(receiptStatus);
        Receipt storedReceipt = receiptRepository.save(persistedReceipt);
        log.info("Receipt with {} id successfully updated. New status {}", storedReceipt.getId(), storedReceipt.getReceiptStatus());
        return receiptMapper.mapReceiptToReceiptDto(persistedReceipt);
    }
}
