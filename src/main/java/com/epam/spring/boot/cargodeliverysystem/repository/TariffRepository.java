package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Tariff;

import java.util.List;

public interface TariffRepository {

    List<Tariff> getAllTariffs();

    Tariff getTariffByName(String tariffName);

    List<Tariff> getTariffsByLanguageId(long id);

    Tariff addTariff(Tariff tariff);

    Tariff updateTariff(String tariffName, Tariff tariff);

    boolean deleteTariff(String tariffName);
}
