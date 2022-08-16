package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryCalculatorService;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeliveryCalculatorController.class)
public class DeliveryCalculatorControllerTest {

    @MockBean
    private DeliveryCalculatorService deliveryCalculatorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calculatePriceShouldReturnPrice100Test() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();

        when(deliveryCalculatorService.calculate(receiptDto)).thenReturn(receiptDto);

        mockMvc.perform(get("/deliveryCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void calculatePriceShouldReturnPrice500AndId5Test() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.setId(5);
        receiptDto.setPrice(500);

        when(deliveryCalculatorService.calculate(receiptDto)).thenReturn(receiptDto);

        mockMvc.perform(get("/deliveryCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(500))
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    void calculatePriceShouldErrorIfReceiptStatusNullTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.setReceiptStatus(null);
        String errorType = "VALIDATION_ERROR_TYPE";
        String errorMessage = "'receiptStatus' shouldn't be empty";

        when(deliveryCalculatorService.calculate(receiptDto)).thenReturn(receiptDto);

        mockMvc.perform(get("/deliveryCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].message").value(errorMessage))
                .andExpect(jsonPath("$[0].errorType").value(errorType));
    }

}
