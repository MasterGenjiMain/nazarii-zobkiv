package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.exception.DeliveryAlreadyDeliveredException;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptManagerService;
import com.epam.spring.boot.cargodeliverysystem.service.ChangeReceiptStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptManagerServiceImpl implements ReceiptManagerService {

    private static final long DELIVERED_STATUS = 6L;
    private static final long CANCELED_STATUS = 8L;
    private static final long APPROVED_STATUS = 2L;

    private final ReceiptRepository receiptRepository;
    private final ReceiptStatusRepository receiptStatusRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final ReceiptStatusMapper receiptStatusMapper;
    private final ChangeReceiptStatusService changeReceiptStatusService;

    @Override
    @Transactional
    public ReceiptDto nextStatus(Long receiptId) {
        log.info("[ApproveServiceImpl] nextStatus with id {}", receiptId);
        Receipt persistedReceipt = receiptRepository.findById(receiptId)
                .orElseThrow(EntityNotFoundException::new);
        Long currentStatusId = persistedReceipt.getReceiptStatus().getId();
        ReceiptStatus newReceiptStatus;
        if (currentStatusId < DELIVERED_STATUS) {
            log.debug("Current receipt with id {} not closed", receiptId);
            newReceiptStatus = receiptStatusRepository.findById(++currentStatusId)
                    .orElseThrow(EntityNotFoundException::new);

            if (currentStatusId == DELIVERED_STATUS) {
                log.debug("Current receipt with id {} pre closed", receiptId);
                DeliveryOrder persistedDeliveryOrder = persistedReceipt.getDeliveryOrder();
                persistedDeliveryOrder.setReceivingDate(new Timestamp(System.currentTimeMillis()));
                deliveryOrderRepository.save(persistedDeliveryOrder);
            }
        } else {
            throw new DeliveryAlreadyDeliveredException();
        }
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(newReceiptStatus));
    }

    @Override
    @Transactional
    public ReceiptDto approve(Long receiptId) {
        log.info("[ApproveServiceImpl] approve with id {}", receiptId);
        ReceiptStatus approveStatus = receiptStatusRepository.findById(APPROVED_STATUS)
                .orElseThrow(EntityNotFoundException::new);
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(approveStatus));
    }

    @Override
    @Transactional
    public ReceiptDto cansel(Long receiptId) {
        log.info("[ApproveServiceImpl] cansel with id {}", receiptId);
        ReceiptStatus canselStatus = receiptStatusRepository.findById(CANCELED_STATUS)
                .orElseThrow(EntityNotFoundException::new);
        return changeReceiptStatus(receiptId, receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(canselStatus));
    }

    private ReceiptDto changeReceiptStatus(Long receiptId, ReceiptStatusDto receiptStatusDto) {
        log.info("[ApproveServiceImpl] changeReceiptStatus with id {}", receiptId);
        return changeReceiptStatusService.changeReceiptStatus(receiptId, receiptStatusDto, receiptRepository);
    }
}
