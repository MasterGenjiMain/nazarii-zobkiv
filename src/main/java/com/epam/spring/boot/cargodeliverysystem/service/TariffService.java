package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;

import java.util.List;

public interface TariffService {

    List<TariffDto> getAllTariffs();

    TariffDto getTariff(String tariffName);

    List<TariffDto> getTariffsByLanguageId(long id);

    TariffDto addTariff(TariffDto tariffDto);

    TariffDto updateTariff(String tariffName, TariffDto tariffDto);

    boolean deleteTariff(String tariffName);
}
