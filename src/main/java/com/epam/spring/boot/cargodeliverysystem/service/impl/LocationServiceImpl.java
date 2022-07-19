package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.LocationMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Location;
import com.epam.spring.boot.cargodeliverysystem.repository.LocationRepository;
import com.epam.spring.boot.cargodeliverysystem.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public List<LocationDto> getAllLocations() {
        log.info("getAllLocations {}", "");
        return locationRepository.getAllLocations()
                .stream()
                .map(locationMapper::mapLocationToLocationDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDto getLocation(String locationName) {
        log.info("getLocation by name {}", locationName);
        Location location = locationRepository.getLocation(locationName);
        return locationMapper.mapLocationToLocationDto(location);
    }

    @Override
    public LocationDto addLocation(LocationDto locationDto) {
        log.info("addLocation with name {}", locationDto.getLocationName());
        Location location = locationMapper.mapLocationDtoToLocation(locationDto);
        location = locationRepository.addLocation(location);
        return locationMapper.mapLocationToLocationDto(location);
    }

    @Override
    public LocationDto updateLocation(String locationName, LocationDto locationDto) {
        log.info("updateLocation with name {}", locationName);
        Location location = locationMapper.mapLocationDtoToLocation(locationDto);

        Location oldLocation = locationRepository.getLocation(locationName);
        location.setId(oldLocation.getId());

        location = locationRepository.updateLocation(locationName, location);
        return locationMapper.mapLocationToLocationDto(location);
    }

    @Override
    public boolean deleteLocation(String locationName) {
        log.info("deleteLocation with name {}", locationName);
        return locationRepository.deleteLocation(locationName);
    }
}
