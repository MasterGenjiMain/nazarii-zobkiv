package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.LocationMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.TariffMapper;
import com.epam.spring.boot.cargodeliverysystem.repository.LocationRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import com.epam.spring.boot.cargodeliverysystem.service.GeneralInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralInfoServiceImpl implements GeneralInfoService {

    private final TariffRepository tariffRepository;
    private final TariffMapper tariffMapper;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public List<TariffDto> getInfoForTariffsTable() {
        log.info("[GeneralInfoServiceImpl] createTariffsTable");
        return tariffRepository.findAll()
                .stream()
                .map(tariffMapper::mapTariffToTariffDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> getInfoForLocationsTable() {
        log.info("[GeneralInfoServiceImpl] createLocationTable");
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::mapLocationToLocationDto)
                .collect(Collectors.toList());
    }
}
