package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.DeliveryRequestServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryRequestServiceImplTest {

    @InjectMocks
    private DeliveryRequestServiceImpl deliveryRequestService;

    @Spy
    private final DeliveryOrderMapper deliveryOrderMapper = new DeliveryOrderMapperImpl();
    @Spy
    private final ReceiptMapper receiptMapper = new ReceiptMapperImpl();
    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private ReceiptRepository receiptRepository;
    @Mock
    private ReceiptStatusRepository receiptStatusRepository;
    @Mock
    private DeliveryCalculatorService deliveryCalculatorService;

    @Test
    void createNewDeliveryRequestTest(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatusDto receiptStatusDto = TestDataUtil.createReceiptStatusDto();
        receiptStatusDto.setStatusName("New");
        testReceiptDto.setReceiptStatus(receiptStatusDto);
        DeliveryOrder testDeliveryOrder = deliveryOrderMapper.mapDeliveryOrderDtoToDeliveryOrder(testReceiptDto.getDeliveryOrder());
        Receipt receipt = receiptMapper.mapReceiptDtoToReceipt(testReceiptDto);

        when(deliveryOrderRepository.save(any())).thenReturn(testDeliveryOrder);
        when(deliveryCalculatorService.calculate(testReceiptDto)).thenReturn(testReceiptDto);
        when(receiptRepository.save(any())).thenReturn(receipt);

        ReceiptDto resultReceiptDto = deliveryRequestService.createNewDeliveryRequest(testReceiptDto);
        assertThat(resultReceiptDto, allOf(
                hasProperty("price", equalTo(testReceiptDto.getPrice())),
                hasProperty("deliveryOrder", equalTo(testReceiptDto.getDeliveryOrder())),
                hasProperty("receiptStatus", equalTo(testReceiptDto.getReceiptStatus()))
        ));
    }

    @Test
    void createNewDeliveryRequestShouldSetNewStatusForAllNewShipmentsTest(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatus receiptStatus = TestDataUtil.createReceiptStatus();
        DeliveryOrder testDeliveryOrder = deliveryOrderMapper.mapDeliveryOrderDtoToDeliveryOrder(testReceiptDto.getDeliveryOrder());
        Receipt receipt = receiptMapper.mapReceiptDtoToReceipt(testReceiptDto);

        when(deliveryOrderRepository.save(any())).thenReturn(testDeliveryOrder);
        when(deliveryCalculatorService.calculate(testReceiptDto)).thenReturn(testReceiptDto);
        when(receiptStatusRepository.findById(1L)).thenReturn(Optional.ofNullable(receiptStatus));
        when(receiptRepository.save(any())).thenReturn(receipt);

        ReceiptDto resultReceiptDto = deliveryRequestService.createNewDeliveryRequest(testReceiptDto);
        assertThat(resultReceiptDto, allOf(
                hasProperty("price", equalTo(testReceiptDto.getPrice())),
                hasProperty("deliveryOrder", equalTo(testReceiptDto.getDeliveryOrder())),
                hasProperty("receiptStatus", equalTo(testReceiptDto.getReceiptStatus()))
        ));
    }
}
