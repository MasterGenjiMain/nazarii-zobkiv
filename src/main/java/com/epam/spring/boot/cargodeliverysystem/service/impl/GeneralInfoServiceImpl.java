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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<TariffDto> getInfoForTariffTableWithName(String languageName, int pageNum, String sortBy) {
        log.info("[GeneralInfoServiceImpl] createTariffsTable");

        Pageable pageable = PageRequest.of(pageNum, 3, Sort.by(sortBy));

        return tariffRepository.findAllByLanguage_LanguageName(languageName, pageable)
                .stream()
                .map(tariffMapper::mapTariffToTariffDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> getInfoForLocationTable(int pageNum, String sortBy) {
        log.info("[GeneralInfoServiceImpl] createLocationTable");

        Pageable pageable = PageRequest.of(pageNum, 3, Sort.by(sortBy));

        return locationRepository.findAll(pageable)
                .stream()
                .map(locationMapper::mapLocationToLocationDto)
                .collect(Collectors.toList());
    }
}
