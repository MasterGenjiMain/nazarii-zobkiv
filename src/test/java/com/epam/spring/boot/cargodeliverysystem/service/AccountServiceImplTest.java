package com.epam.spring.boot.cargodeliverysystem.service;


import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.AccountServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Spy
    private final ReceiptMapper receiptMapper = new ReceiptMapperImpl();
    @Spy
    private final ReceiptStatusMapper receiptStatusMapper = new ReceiptStatusMapperImpl();
    @Mock
    private ReceiptRepository receiptRepository;
    @Mock
    private ChangeReceiptStatusService changeReceiptStatusService;
    @Mock
    private ReceiptStatusRepository receiptStatusRepository;

    @Test
    void getAllUserReceiptsTest(){
        List<Receipt> receiptList = TestDataUtil.createReceiptList();
        when(receiptRepository.findAllByUserId(eq(1L), any())).thenReturn(receiptList);

        List<ReceiptDto> receiptDtoList = accountService.getAllUserReceipts(1L, 0, "id");

        assertThat(receiptDtoList, hasSize(3));
    }

    @Test
    void payTest(){
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatusDto paidReceiptStatus = TestDataUtil.createReceiptStatusDto();
        paidReceiptStatus.setStatusName("PaidStatus");
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(paidReceiptStatus);
        receiptDto.setReceiptStatus(paidReceiptStatus);
        when(receiptStatusRepository.findById(anyLong())).thenReturn(Optional.of(receiptStatus));
        when(changeReceiptStatusService.changeReceiptStatus(1L, paidReceiptStatus, receiptRepository)).thenReturn(receiptDto);

        ReceiptDto resultReceiptDto = accountService.pay(1L);

        assertThat(resultReceiptDto.getReceiptStatus(), equalTo(paidReceiptStatus));
    }

    @Test
    void canselTest(){
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatusDto canceledStatus = TestDataUtil.createReceiptStatusDto();
        canceledStatus.setStatusName("CanceledStatus");
        ReceiptStatus receiptStatus = receiptStatusMapper.mapReceiptStatusDtoToReceiptStatus(canceledStatus);
        receiptDto.setReceiptStatus(canceledStatus);
        when(receiptStatusRepository.findById(anyLong())).thenReturn(Optional.of(receiptStatus));
        when(changeReceiptStatusService.changeReceiptStatus(1L, canceledStatus, receiptRepository)).thenReturn(receiptDto);

        ReceiptDto resultReceiptDto = accountService.cansel(1L);

        assertThat(resultReceiptDto.getReceiptStatus(), equalTo(canceledStatus));
    }

}
