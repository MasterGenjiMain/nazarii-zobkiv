package com.epam.spring.boot.cargodeliverysystem.service;

public interface DeliveryCalculatorService {

    double calculate(int height, int width, int length,
                 double distance, double weight, String tariffName);
}
