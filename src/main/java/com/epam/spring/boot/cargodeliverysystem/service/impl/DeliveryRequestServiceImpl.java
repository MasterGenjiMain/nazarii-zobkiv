package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryCalculatorService;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryRequestServiceImpl implements DeliveryRequestService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryOrderMapper deliveryOrderMapper;
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final ReceiptStatusRepository receiptStatusRepository;
    private final DeliveryCalculatorService deliveryCalculatorService;

    @Override
    @Transactional
    public ReceiptDto createNewDeliveryRequest(ReceiptDto receiptDto) {
        DeliveryOrderDto newDeliveryOrderDto = createNewDeliveryOrder(receiptDto.getDeliveryOrder());
        return createNewReceipt(receiptDto, newDeliveryOrderDto);
    }

    private DeliveryOrderDto createNewDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        log.info("[DeliveryRequestServiceImpl] createNewDeliveryRequest");
        DeliveryOrder deliveryOrder = deliveryOrderMapper.mapDeliveryOrderDtoToDeliveryOrder(deliveryOrderDto);
        deliveryOrder = deliveryOrderRepository.save(deliveryOrder);
        log.info("[DeliveryRequestServiceImpl] Delivery order with id {} successfully created", deliveryOrder.getId());
        return deliveryOrderMapper.mapDeliveryOrderToDeliveryOrderDto(deliveryOrder);
    }

    private ReceiptDto createNewReceipt(ReceiptDto receiptDto, DeliveryOrderDto newDeliveryOrderDto) {
        receiptDto = deliveryCalculatorService.calculate(receiptDto);
        Receipt receipt = receiptMapper.mapReceiptDtoToReceipt(receiptDto);
        if (!receipt.getReceiptStatus().getStatusName().equals("New")){
            log.info("[DeliveryRequestServiceImpl] force set status 'New'");
            ReceiptStatus newReceiptStatus = receiptStatusRepository.findById(1L)
                    .orElseThrow(EntityNotFoundException::new);
            receipt.setReceiptStatus(newReceiptStatus);
        }
        DeliveryOrder deliveryOrder = deliveryOrderMapper
                .mapDeliveryOrderDtoToDeliveryOrder(newDeliveryOrderDto);
        receipt.setDeliveryOrder(deliveryOrder);
        receipt = receiptRepository.save(receipt);
        return receiptMapper.mapReceiptToReceiptDto(receipt);
    }

}
