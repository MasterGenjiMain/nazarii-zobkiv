package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryOrderDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryCalculatorServiceImpl implements DeliveryCalculatorService {

    private static final int VOLUME_DIVIDER = 1000;
    private static final int VOLUME_PRICE = 2;
    private static final int WEIGHT_PRICE = 5;

    private final TariffRepository tariffRepository;

    @Override
    public ReceiptDto calculate(ReceiptDto receiptDto) {
        log.info("[DeliveryCalculatorImpl] calculate");

        DeliveryOrderDto deliveryOrderDto = receiptDto.getDeliveryOrder();

        log.debug("volume -> {}", deliveryOrderDto.getVolume());
        log.debug("distance -> {}", deliveryOrderDto.getDistance());
        log.debug("weight -> {}", deliveryOrderDto.getWeight());
        log.debug("tariffName -> {}", deliveryOrderDto.getTariff().getTariffName());

        return getPrice(receiptDto, deliveryOrderDto);
    }

    private ReceiptDto getPrice(ReceiptDto receiptDto, DeliveryOrderDto deliveryOrderDto) {
        log.info("[DeliveryCalculatorImpl] getPrice");
        double volumePrice;
        double weightPrice;
        double price;
        double MINIMAL_PRICE;

        double distanceMultiplayer = getDistanceMultiplayer(deliveryOrderDto.getDistance());
        log.info("distanceMultiplayer -> {}", distanceMultiplayer);

        volumePrice = ((deliveryOrderDto.getVolume() / VOLUME_DIVIDER) * VOLUME_PRICE) * distanceMultiplayer;
        weightPrice = (deliveryOrderDto.getWeight() * WEIGHT_PRICE) * distanceMultiplayer;
        log.info("volumePrice -> {}", volumePrice);
        log.info("weightPrice -> {}", weightPrice);

        price = Math.max(volumePrice, weightPrice);
        Tariff persistedTariff = tariffRepository
                .findByTariffName(deliveryOrderDto.getTariff().getTariffName())
                .orElseThrow(EntityNotFoundException::new);
        MINIMAL_PRICE = persistedTariff.getTariffPrice();

        log.info("before min -> {}", price);
        if (price < MINIMAL_PRICE) {
            price = MINIMAL_PRICE;
        }

        receiptDto.setPrice(price);
        log.info("after min -> {}", price);
        return receiptDto;
    }

    private double getDistanceMultiplayer(double distance) {
        log.info("[DeliveryCalculatorImpl] getDistanceMultiplayer");
        double distanceMultiplayer;
        if (distance <= 100) {
            distanceMultiplayer = 1;
        } else if (distance <= 500) {
            distanceMultiplayer = 1.1;
        } else if (distance <= 1000) {
            distanceMultiplayer = 1.3;
        } else {
            distanceMultiplayer = 1.5;
        }
        return distanceMultiplayer;
    }
}
