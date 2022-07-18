package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.CityDto;
import com.epam.spring.boot.cargodeliverysystem.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City mapCityDtoToCity(CityDto cityDto);

    CityDto mapCityToCityDto(City city);
}
