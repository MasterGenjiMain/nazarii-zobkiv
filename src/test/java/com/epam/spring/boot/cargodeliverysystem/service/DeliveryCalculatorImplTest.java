package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.DeliveryCalculatorServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryCalculatorImplTest {

    @InjectMocks
    private DeliveryCalculatorServiceImpl deliveryCalculatorService;

    @Mock
    private TariffRepository tariffRepository;

    @Test
    void calculateShouldReturn500WhenTheWeight100Test(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        Tariff tariff = TestDataUtil.createTariff();

        when(tariffRepository.findByTariffName(testReceiptDto.getDeliveryOrder().getTariff().getTariffName()))
                .thenReturn(Optional.ofNullable(tariff));

        ReceiptDto resultReceiptDto = deliveryCalculatorService.calculate(testReceiptDto);

        assertThat(resultReceiptDto.getPrice(), equalTo(500.0));
    }

    @Test
    void calculateShouldAddExtraPriceForDistanceHigher500AndLess1000Test(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        Tariff tariff = TestDataUtil.createTariff();
        testReceiptDto.getDeliveryOrder().setDistance(1000);

        when(tariffRepository.findByTariffName(testReceiptDto.getDeliveryOrder().getTariff().getTariffName()))
                .thenReturn(Optional.ofNullable(tariff));

        ReceiptDto resultReceiptDto = deliveryCalculatorService.calculate(testReceiptDto);

        assertThat(resultReceiptDto.getPrice(), equalTo(650.0));
    }

    @Test
    void calculateShouldAddExtraPriceForDistanceHigher100AndLess500Test(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        Tariff tariff = TestDataUtil.createTariff();
        testReceiptDto.getDeliveryOrder().setDistance(500);

        when(tariffRepository.findByTariffName(testReceiptDto.getDeliveryOrder().getTariff().getTariffName()))
                .thenReturn(Optional.ofNullable(tariff));

        ReceiptDto resultReceiptDto = deliveryCalculatorService.calculate(testReceiptDto);

        assertThat(resultReceiptDto.getPrice(), equalTo(550.0));
    }

    @Test
    void calculateShouldNotAddExtraPriceForDistanceLess100Test(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        Tariff tariff = TestDataUtil.createTariff();
        testReceiptDto.getDeliveryOrder().setDistance(100);

        when(tariffRepository.findByTariffName(testReceiptDto.getDeliveryOrder().getTariff().getTariffName()))
                .thenReturn(Optional.ofNullable(tariff));

        ReceiptDto resultReceiptDto = deliveryCalculatorService.calculate(testReceiptDto);

        assertThat(resultReceiptDto.getPrice(), equalTo(500.0));
    }

    @Test
    void calculateShouldNotAddExtraPriceForDistanceHigher1000Test(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        Tariff tariff = TestDataUtil.createTariff();
        testReceiptDto.getDeliveryOrder().setDistance(1100);

        when(tariffRepository.findByTariffName(testReceiptDto.getDeliveryOrder().getTariff().getTariffName()))
                .thenReturn(Optional.ofNullable(tariff));

        ReceiptDto resultReceiptDto = deliveryCalculatorService.calculate(testReceiptDto);

        assertThat(resultReceiptDto.getPrice(), equalTo(750.0));
    }

    @Test
    void calculateShouldSetMinimalPriceFromTariffIfGeneralPriceLessTest(){
        ReceiptDto testReceiptDto = TestDataUtil.createReceiptDto();
        Tariff tariff = TestDataUtil.createTariff();
        testReceiptDto.getDeliveryOrder().setDistance(50);
        testReceiptDto.getDeliveryOrder().setWeight(5);

        when(tariffRepository.findByTariffName(testReceiptDto.getDeliveryOrder().getTariff().getTariffName()))
                .thenReturn(Optional.ofNullable(tariff));

        ReceiptDto resultReceiptDto = deliveryCalculatorService.calculate(testReceiptDto);

        assertThat(resultReceiptDto.getPrice(), equalTo(Objects.requireNonNull(tariff).getTariffPrice()));
    }
}
