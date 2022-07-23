package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.CityDto;

public interface CityService {

    CityDto getCity(String name);

    CityDto addCity(CityDto cityDto);

    CityDto updateCity(String name, CityDto cityDto);

    boolean deleteCity(String name);
}
