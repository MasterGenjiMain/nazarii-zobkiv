package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.exception.DeliveryAlreadyDeliveredException;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptManagerService;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReceiptManagerController.class)
public class ReceiptManagerControllerTest {

    @MockBean
    private ReceiptManagerService receiptManagerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void setNextStatusTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.getReceiptStatus().setId(4);
        ReceiptDto returnedReceiptDto = TestDataUtil.createReceiptDto();
        returnedReceiptDto.getReceiptStatus().setId(5);

        when(receiptManagerService.nextStatus(receiptDto.getId())).thenReturn(returnedReceiptDto);

        mockMvc.perform(put("/receiptManager/nextStatus/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.receiptStatus.id").value(5));
    }

    @Test
    void approveReceiptTest() throws Exception{
        int unapprovedStatusId = 1;
        int approvedStatusId = 2;
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.getReceiptStatus().setId(unapprovedStatusId);
        ReceiptDto returnedReceiptDto = TestDataUtil.createReceiptDto();
        returnedReceiptDto.getReceiptStatus().setId(approvedStatusId);

        when(receiptManagerService.approve(receiptDto.getId())).thenReturn(returnedReceiptDto);

        mockMvc.perform(put("/receiptManager/approve/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.receiptStatus.id").value(approvedStatusId));
    }

    @Test
    void cancelReceiptTest() throws Exception{
        int randomUndeliveredStatusId = 3;
        int canceledStatusId = 8;
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.getReceiptStatus().setId(randomUndeliveredStatusId);
        ReceiptDto returnedReceiptDto = TestDataUtil.createReceiptDto();
        returnedReceiptDto.getReceiptStatus().setId(canceledStatusId);

        when(receiptManagerService.cancel(receiptDto.getId())).thenReturn(returnedReceiptDto);

        mockMvc.perform(put("/receiptManager/cancel/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.receiptStatus.id").value(canceledStatusId));
    }

    @Test
    void setNextStatusShouldReturnDeliveryAlreadyDeliveredExceptionTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        String errorType = "DATABASE_ERROR_TYPE";

        when(receiptManagerService.nextStatus(receiptDto.getId())).thenThrow(DeliveryAlreadyDeliveredException.class);

        mockMvc.perform(put("/receiptManager/nextStatus/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorType").value(errorType));
    }
}
