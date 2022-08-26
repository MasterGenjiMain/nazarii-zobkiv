package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.CityDto;
import com.epam.spring.boot.cargodeliverysystem.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}")
    public CityDto getCity(@PathVariable String name) {
        log.info("[Controller] getCity by name {} ", name);
        return cityService.getCity(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CityDto addCity(@RequestBody CityDto cityDto) {
        log.info("[Controller] addCity {} ", cityDto);
        return cityService.addCity(cityDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{name}")
    public CityDto updateCity(@PathVariable String name, @RequestBody CityDto cityDto) {
        log.info("[Controller] updateCity by name {} ", name);
        return cityService.updateCity(name, cityDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{name}")
    public boolean deleteCity(@PathVariable String name) {
        log.info("[Controller] deleteCity by name {} ", name);
        return cityService.deleteCity(name);
    }
}
