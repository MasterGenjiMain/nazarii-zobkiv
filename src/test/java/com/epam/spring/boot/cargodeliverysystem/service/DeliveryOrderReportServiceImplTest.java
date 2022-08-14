package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.DeliveryOrderMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.DeliveryOrder;
import com.epam.spring.boot.cargodeliverysystem.repository.DeliveryOrderRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.DeliveryOrderReportServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryOrderReportServiceImplTest {

    @InjectMocks
    private DeliveryOrderReportServiceImpl deliveryOrderReportService;

    @Spy
    private final DeliveryOrderMapper deliveryOrderMapper = new DeliveryOrderMapperImpl();
    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;

    @Test
    void getAllDeliveryOrdersTest(){
        List<DeliveryOrder> deliveryOrderList = TestDataUtil.createDeliveryOrderList();
        List<DeliveryOrderDto> expectedDeliveryOrderDtoList = TestDataUtil.createDeliveryOrderDtoList();
        Page<DeliveryOrder> page = new PageImpl<>(deliveryOrderList);

        when(deliveryOrderRepository.findAll(any(Pageable.class))).thenReturn(page);

        List<DeliveryOrderDto> deliveryOrderDtoList = deliveryOrderReportService.getAllDeliveryOrders(0, "id");

        assertThat(deliveryOrderDtoList, equalTo(expectedDeliveryOrderDtoList));
    }
}
