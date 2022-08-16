package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.service.AccountService;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUserReceiptsTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserDto();
        List<ReceiptDto> receiptDtoList = TestDataUtil.createReceiptDtoList();
        when(accountService.getAllUserReceipts(userDto.getId(), 1, "id")).thenReturn(receiptDtoList);

        mockMvc.perform(get("/account/" + userDto.getId() + "?pageNum=1&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].user.id").value(userDto.getId()));
    }

    @Test
    void payTest() throws Exception {
        int unpaidStatusId = 2;
        int paidStatusId = 3;
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.getReceiptStatus().setId(unpaidStatusId);
        ReceiptDto returnedReceiptDto = TestDataUtil.createReceiptDto();
        returnedReceiptDto.getReceiptStatus().setId(paidStatusId);

        when(accountService.pay(receiptDto.getId())).thenReturn(returnedReceiptDto);

        mockMvc.perform(put("/account/pay/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.receiptStatus.id").value(paidStatusId));
    }

    @Test
    void cancelTest() throws Exception {
        int randomUndeliveredStatusId = 5;
        int canceledStatusId = 8;
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.getReceiptStatus().setId(randomUndeliveredStatusId);
        ReceiptDto returnedReceiptDto = TestDataUtil.createReceiptDto();
        returnedReceiptDto.getReceiptStatus().setId(canceledStatusId);

        when(accountService.pay(receiptDto.getId())).thenReturn(returnedReceiptDto);

        mockMvc.perform(put("/account/pay/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.receiptStatus.id").value(canceledStatusId));
    }

    @Test
    void entityNotFoundExceptionTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        String errorType = "DATABASE_ERROR_TYPE";

        when(accountService.pay(receiptDto.getId())).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(put("/account/pay/" + receiptDto.getId()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorType").value(errorType));
    }
}
