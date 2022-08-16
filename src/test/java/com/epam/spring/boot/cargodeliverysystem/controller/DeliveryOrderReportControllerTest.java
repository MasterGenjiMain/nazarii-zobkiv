package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryOrderReportService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeliveryOrderReportController.class)
public class DeliveryOrderReportControllerTest {

    @MockBean
    private DeliveryOrderReportService deliveryOrderReportService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllDeliveryOrdersShouldHaveCorrectWeightInfoTest() throws Exception {
        List<DeliveryOrderDto> deliveryOrderDtoList = TestDataUtil.createDeliveryOrderDtoList();
        when(deliveryOrderReportService.getAllDeliveryOrders(0,"id")).thenReturn(deliveryOrderDtoList);

        mockMvc.perform(get("/deliveryOrderReport?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].weight").value(deliveryOrderDtoList.get(0).getWeight()));

    }

    @Test
    void getAllDeliveryOrdersShouldHaveCorrectVolumeInfoTest() throws Exception {
        List<DeliveryOrderDto> deliveryOrderDtoList = TestDataUtil.createDeliveryOrderDtoList();
        when(deliveryOrderReportService.getAllDeliveryOrders(0,"id")).thenReturn(deliveryOrderDtoList);

        mockMvc.perform(get("/deliveryOrderReport?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].volume").value(deliveryOrderDtoList.get(1).getVolume()));

    }

    @Test
    void getAllDeliveryOrdersShouldHaveCorrectLocationNameTest() throws Exception {
        List<DeliveryOrderDto> deliveryOrderDtoList = TestDataUtil.createDeliveryOrderDtoList();
        when(deliveryOrderReportService.getAllDeliveryOrders(0,"id")).thenReturn(deliveryOrderDtoList);

        mockMvc.perform(get("/deliveryOrderReport?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[2].locationFrom.locationName").value(deliveryOrderDtoList.get(2).getLocationFrom().getLocationName()));

    }
}
