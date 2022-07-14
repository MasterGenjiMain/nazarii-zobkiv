package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;

public interface LocationService {

    LocationDto getLocation(String locationName);

    LocationDto addLocation(LocationDto locationDto);

    LocationDto updateLocation(String locationName, LocationDto locationDto);

    boolean deleteLocation(String locationName);
}
