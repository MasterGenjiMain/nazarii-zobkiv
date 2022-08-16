package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeliveryRequestController.class)
public class DeliveryRequestControllerTest {

    @MockBean
    private DeliveryRequestController deliveryRequestController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createNewDeliveryRequestTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();

        when(deliveryRequestController.createNewDeliveryRequest(receiptDto)).thenReturn(receiptDto);

        mockMvc.perform(post("/deliveryRequest/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(receiptDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(receiptDto.getId()));
    }

    @Test
    void createNewDeliveryRequestValidationTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();
        receiptDto.setReceiptStatus(null);
        String message = "'receiptStatus' shouldn't be empty";
        String errorType = "VALIDATION_ERROR_TYPE";

        when(deliveryRequestController.createNewDeliveryRequest(receiptDto)).thenReturn(receiptDto);

        mockMvc.perform(post("/deliveryRequest/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(message))
                .andExpect(jsonPath("$[0].errorType").value(errorType));
    }

    @Test
    void createNewDeliveryRequestShouldContainValidInformationAboutUserTest() throws Exception {
        ReceiptDto receiptDto = TestDataUtil.createReceiptDto();

        when(deliveryRequestController.createNewDeliveryRequest(receiptDto)).thenReturn(receiptDto);

        mockMvc.perform(post("/deliveryRequest/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.id").value(receiptDto.getUser().getId()))
                .andExpect(jsonPath("$.user.username").value(receiptDto.getUser().getUsername()))
                .andExpect(jsonPath("$.user.email").value(receiptDto.getUser().getEmail()))
                .andExpect(jsonPath("$.price").value(receiptDto.getPrice()));
    }

}
