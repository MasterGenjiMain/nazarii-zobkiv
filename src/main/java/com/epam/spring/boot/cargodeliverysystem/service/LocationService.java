package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;

import java.util.List;

public interface LocationService {

    List<LocationDto> getAllLocations();

    LocationDto getLocation(String locationName);

    LocationDto addLocation(LocationDto locationDto);

    LocationDto updateLocation(String locationName, LocationDto locationDto);

    boolean deleteLocation(String locationName);
}
