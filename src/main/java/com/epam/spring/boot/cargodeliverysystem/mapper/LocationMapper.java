package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto mapLocationToLocationDto(Location location);

    Location mapLocationDtoToLocation(LocationDto locationDto);
}
