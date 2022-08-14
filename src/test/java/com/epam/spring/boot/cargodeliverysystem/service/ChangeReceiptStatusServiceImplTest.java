package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptStatusMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.ChangeReceiptStatusServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeReceiptStatusServiceImplTest {

    @InjectMocks
    private ChangeReceiptStatusServiceImpl changeReceiptStatusService;

    @Spy
    private final ReceiptStatusMapper receiptStatusMapper = new ReceiptStatusMapperImpl();
    @Spy
    private final ReceiptMapper receiptMapper = new ReceiptMapperImpl();
    @Mock
    private ReceiptRepository receiptRepository;

    @Test
    void changeReceiptStatusTest(){
        Receipt receipt = TestDataUtil.createReceipt();
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        ReceiptStatusDto receiptStatusDto = TestDataUtil.createReceiptStatusDto();
        receiptStatusDto.setStatusName("changedName");
        when(receiptRepository.findById(testReceiptDto.getId())).thenReturn(Optional.of(receipt));
        when(receiptRepository.save(any())).thenReturn(receipt);

        ReceiptDto receiptDto = changeReceiptStatusService.changeReceiptStatus(receipt.getId(), receiptStatusDto, receiptRepository);

        assertThat(receiptDto.getReceiptStatus(), equalTo(receiptStatusDto));
    }
}
