package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.model.Location;
import com.epam.spring.boot.cargodeliverysystem.repository.LocationRepository;
import com.epam.spring.boot.cargodeliverysystem.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public LocationDto getLocation(String locationName) {
        log.info("getLocation by name {}", locationName);
        Location location = locationRepository.getLocation(locationName);
        return mapLocationToLocationDto(location);
    }

    @Override
    public LocationDto addLocation(LocationDto locationDto) {
        log.info("addLocation with name {}", locationDto.getLocationName());
        Location location = mapLocationDtoToLocation(locationDto);
        location = locationRepository.addLocation(location);
        return mapLocationToLocationDto(location);
    }

    @Override
    public LocationDto updateLocation(String locationName, LocationDto locationDto) {
        log.info("updateLocation with name {}", locationName);
        Location location = mapLocationDtoToLocation(locationDto);
        location = locationRepository.updateLocation(locationName, location);
        return mapLocationToLocationDto(location);
    }

    @Override
    public boolean deleteLocation(String locationName) {
        log.info("deleteLocation with name {}", locationName);
        return locationRepository.deleteLocation(locationName);
    }

    private LocationDto mapLocationToLocationDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .locationName(location.getLocationName())
                .cityId(location.getCityId())
                .activeStatusId(location.getActiveStatusId())
                .build();
    }

    private Location mapLocationDtoToLocation(LocationDto locationDto) {
        return Location.builder()
                .id(locationDto.getId())
                .locationName(locationDto.getLocationName())
                .cityId(locationDto.getCityId())
                .activeStatusId(locationDto.getActiveStatusId())
                .build();
    }
}
