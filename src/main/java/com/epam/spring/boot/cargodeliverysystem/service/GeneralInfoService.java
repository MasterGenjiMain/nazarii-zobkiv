package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;

import java.util.List;

public interface GeneralInfoService {

    List<TariffDto> getInfoForTariffsTable();

    List<LocationDto> getInfoForLocationsTable();
}
