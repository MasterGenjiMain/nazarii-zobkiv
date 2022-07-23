package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TariffMapper {

    TariffDto mapTariffToTariffDto(Tariff tariff);

    Tariff mapTariffDtoToTariff(TariffDto tariffDto);
}
