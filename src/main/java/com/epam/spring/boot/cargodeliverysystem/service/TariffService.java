package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;

public interface TariffService {

    TariffDto getTariff(String tariffName);

    TariffDto addTariff(TariffDto tariffDto);

    TariffDto updateTariff(String tariffName, TariffDto tariffDto);

    boolean deleteTariff(String tariffName);
}
