package com.epam.spring.boot.cargodeliverysystem.service.impl;

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
public class DeliveryCalculatorImpl implements DeliveryCalculatorService {

    private static final int VOLUME_DIVIDER = 1000;
    private static final int VOLUME_PRICE = 2;
    private static final int WEIGHT_PRICE = 5;

    private final TariffRepository tariffRepository;

    @Override
    public double calculate(int height, int width, int length, double distance, double weight, String tariffName) {
        log.info("[DeliveryCalculatorImpl] calculate");
        double volume;

        log.debug("height -> {}", height);
        log.debug("width -> {}", width);
        log.debug("length -> {}", length);
        log.debug("distance -> {}", distance);
        log.debug("weight -> {}", weight);
        log.debug("tariffName -> {}", tariffName);

        volume = height * width * length;
        log.debug("volume -> {}", volume);

        return getPrice(distance, weight, volume, tariffName);
    }

    private double getPrice(double distance, double weight, double volume, String tariffName) {
        log.info("[DeliveryCalculatorImpl] getPrice");
        double volumePrice;
        double weightPrice;
        double price;
        double MINIMAL_PRICE;

        double distanceMultiplayer = getDistanceMultiplayer(distance);
        log.info("distanceMultiplayer -> {}", distanceMultiplayer);

        volumePrice = ((volume / VOLUME_DIVIDER) * VOLUME_PRICE) * distanceMultiplayer;
        weightPrice = (weight * WEIGHT_PRICE) * distanceMultiplayer;
        log.info("volumePrice -> {}", volumePrice);
        log.info("weightPrice -> {}", weightPrice);

        price = Math.max(volumePrice, weightPrice);
        Tariff tariff = tariffRepository.findByTariffName(tariffName)
                .orElseThrow(EntityNotFoundException::new);
        MINIMAL_PRICE = tariff.getTariffPrice();

        log.info("before min -> {}", price);
        if (price < MINIMAL_PRICE) {
            price = MINIMAL_PRICE;
        }
        log.info("after min -> {}", price);
        return price;
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
