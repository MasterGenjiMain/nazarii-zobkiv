package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.CityDto;
import com.epam.spring.boot.cargodeliverysystem.model.City;
import com.epam.spring.boot.cargodeliverysystem.repository.CityRepository;
import com.epam.spring.boot.cargodeliverysystem.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityDto getCity(String name) {
        log.info("getCity by name {}", name);
        City city = cityRepository.getCity(name);
        return mapCityToCityDto(city);
    }

    @Override
    public CityDto addCity(CityDto cityDto) {
        log.info("addCity with name {}", cityDto.getCityName());
        City city = mapCityDtoToCity(cityDto);
        city = cityRepository.addCity(city);
        return mapCityToCityDto(city);
    }

    @Override
    public CityDto updateCity(String name, CityDto cityDto) {
        log.info("updateUser with name {}", name);
        City city = mapCityDtoToCity(cityDto);
        city = cityRepository.updateCity(name, city);
        return mapCityToCityDto(city);
    }

    @Override
    public boolean deleteCity(String name) {
        log.info("deleteUser with name {}", name);
        return cityRepository.deleteCity(name);
    }

    private CityDto mapCityToCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .build();
    }

    private City mapCityDtoToCity(CityDto cityDto) {
        return City.builder()
                .id(cityDto.getId())
                .cityName(cityDto.getCityName())
                .build();
    }
}
