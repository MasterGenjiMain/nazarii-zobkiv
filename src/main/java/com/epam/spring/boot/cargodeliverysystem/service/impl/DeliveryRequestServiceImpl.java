package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryRequestServiceImpl implements DeliveryRequestService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryOrderMapper deliveryOrderMapper;
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public ReceiptDto createNewDeliveryRequest(DeliveryOrderDto deliveryOrderDto, ReceiptDto receiptDto) {
        log.info("[DeliveryRequestServiceImpl] createNewDeliveryRequest");
        DeliveryOrder deliveryOrder = deliveryOrderMapper.mapDeliveryOrderDtoToDeliveryOrder(deliveryOrderDto);
        deliveryOrder = deliveryOrderRepository.save(deliveryOrder);
        log.info("[DeliveryRequestServiceImpl] Delivery order with id {} successfully created", deliveryOrder.getId());
        Receipt receipt = receiptMapper.mapReceiptDtoToReceipt(receiptDto);
        receipt.setDeliveryOrder(deliveryOrder);
        receipt = receiptRepository.save(receipt);
        return receiptMapper.mapReceiptToReceiptDto(receipt);
    }
}
