package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.epam.spring.boot.cargodeliverysystem.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{locName}")
    public LocationDto getLocation(@PathVariable String locName) {
        log.info("[Controller] getLocation by name {} ", locName);
        return locationService.getLocation(locName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<LocationDto> getAllLocations(){
        log.info("[Controller] getAllLocations {} ", "");
        return locationService.getAllLocations();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LocationDto addLocation(@RequestBody @Validated(OnCreate.class) LocationDto locationDto) {
        log.info("[Controller] addLocation {} ", locationDto);
        return locationService.addLocation(locationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{locName}")
    public LocationDto updateLocation(@PathVariable String locName, @RequestBody @Validated(OnUpdate.class) LocationDto locationDto) {
        log.info("[Controller] updateLocation by name {} ", locName);
        return locationService.updateLocation(locName, locationDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{locName}")
    public boolean deleteLocation(@PathVariable String locName) {
        log.info("[Controller] deleteLocation by name {} ", locName);
        return locationService.deleteLocation(locName);
    }
}
