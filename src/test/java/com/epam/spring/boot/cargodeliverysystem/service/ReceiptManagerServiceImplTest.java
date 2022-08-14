package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.exception.DeliveryAlreadyDeliveredException;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.ReceiptManagerServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiptManagerServiceImplTest {

    @InjectMocks
    private ReceiptManagerServiceImpl receiptManagerService;

    @Spy
    private final ReceiptStatusMapper receiptStatusMapper = new ReceiptStatusMapperImpl();
    @Mock
    private ReceiptRepository receiptRepository;
    @Mock
    private ReceiptStatusRepository receiptStatusRepository;
    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private ChangeReceiptStatusService changeReceiptStatusService;

    @Test
    void approveTest(){
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatusDto approvedStatus = TestDataUtil.createReceiptStatusDto();
        approvedStatus.setStatusName("ApprovedStatus");
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(approvedStatus);
        receiptDto.setReceiptStatus(approvedStatus);
        when(receiptStatusRepository.findById(anyLong())).thenReturn(Optional.of(receiptStatus));
        when(changeReceiptStatusService.changeReceiptStatus(1L, approvedStatus, receiptRepository)).thenReturn(receiptDto);

        ReceiptDto resultReceiptDto = receiptManagerService.approve(1L);

        assertThat(resultReceiptDto.getReceiptStatus(), equalTo(approvedStatus));
    }

    @Test
    void cancelTest(){
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatusDto canceledStatus = TestDataUtil.createReceiptStatusDto();
        canceledStatus.setStatusName("canceledStatus");
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(canceledStatus);
        receiptDto.setReceiptStatus(canceledStatus);
        when(receiptStatusRepository.findById(anyLong())).thenReturn(Optional.of(receiptStatus));
        when(changeReceiptStatusService.changeReceiptStatus(1L, canceledStatus, receiptRepository)).thenReturn(receiptDto);

        ReceiptDto resultReceiptDto = receiptManagerService.cansel(1L);

        assertThat(resultReceiptDto.getReceiptStatus(), equalTo(canceledStatus));
    }

    @Test
    void nextStatusTest(){
        Receipt testReceipt = TestDataUtil.createReceipt();
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatus nextStatus = TestDataUtil.createReceiptStatus();
        nextStatus.setId(2L);
        nextStatus.setStatusName("nextStatusName");
        testReceiptDto.setReceiptStatus(receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(nextStatus));
        when(receiptRepository.findById(anyLong())).thenReturn(Optional.of(testReceipt));
        when(receiptStatusRepository.findById(anyLong()))
                .thenReturn(Optional.of(nextStatus));
        when(changeReceiptStatusService.changeReceiptStatus(1L,
                receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(nextStatus), receiptRepository))
                .thenReturn(testReceiptDto);

        ReceiptDto resultReceiptDto = receiptManagerService.nextStatus(1L);
        assertThat(resultReceiptDto.getReceiptStatus(),
                equalTo(receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(nextStatus)));
    }

    @Test
    void nextStatusShouldThrowDeliveryAlreadyDeliveredExceptionIfStatusId6OrHigher(){
        Receipt testReceipt = TestDataUtil.createReceipt();
        testReceipt.getReceiptStatus().setId(6L);
        when(receiptRepository.findById(anyLong())).thenReturn(Optional.of(testReceipt));

        assertThrows(DeliveryAlreadyDeliveredException.class, () -> receiptManagerService.nextStatus(testReceipt.getId()));
    }

    @Test
    void nextStatusShouldSetActualDateWhenStatusDeliveredTest(){
        Receipt testReceipt = TestDataUtil.createReceipt();
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatus nextStatus = TestDataUtil.createReceiptStatus();
        testReceipt.getReceiptStatus().setId(5L);
        nextStatus.setId(6L);
        nextStatus.setStatusName("nextStatusName");
        testReceiptDto.setReceiptStatus(receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(nextStatus));
        testReceiptDto.getDeliveryOrder().setReceivingDate(new Timestamp(System.currentTimeMillis()));
        when(receiptRepository.findById(testReceipt.getId())).thenReturn(Optional.of(testReceipt));
        when(receiptStatusRepository.findById(6L))
                .thenReturn(Optional.of(nextStatus));
        when(deliveryOrderRepository.save(any())).thenReturn(testReceipt.getDeliveryOrder());
        when(changeReceiptStatusService.changeReceiptStatus(1L,
                receiptStatusMapper.mapReceiptStatusToReceiptStatusDto(nextStatus), receiptRepository))
                .thenReturn(testReceiptDto);

        ReceiptDto resultReceiptDto = receiptManagerService.nextStatus(1L);
        assertThat(resultReceiptDto.getDeliveryOrder().getReceivingDate(), notNullValue());
    }
}
