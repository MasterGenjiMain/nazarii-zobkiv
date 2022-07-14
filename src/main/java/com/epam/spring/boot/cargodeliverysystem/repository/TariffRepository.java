package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Tariff;

public interface TariffRepository {

    Tariff getTariff(String tariffName);

    Tariff addTariff(Tariff tariff);

    Tariff updateTariff(String tariffName, Tariff tariff);

    boolean deleteTariff(String tariffName);
}
