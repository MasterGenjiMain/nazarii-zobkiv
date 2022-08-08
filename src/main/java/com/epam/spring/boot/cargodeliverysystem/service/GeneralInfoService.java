package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;

import java.util.List;

public interface GeneralInfoService {

    List<TariffDto> getInfoForTariffTableWithName(String languageName, int pageNum, String sortBy);

    List<LocationDto> getInfoForLocationTable(int pageNum, String sortBy);
}
