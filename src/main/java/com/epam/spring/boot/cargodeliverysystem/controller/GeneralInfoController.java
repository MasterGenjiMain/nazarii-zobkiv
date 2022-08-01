package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.service.GeneralInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/generalInfo")
@RequiredArgsConstructor
public class GeneralInfoController {

    private final GeneralInfoService generalInfoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "tariffInfo")
    public List<TariffDto> getTariffsInfo(){
        log.info("[GeneralInfoController] getTariffsInfo");
        return generalInfoService.getInfoForTariffsTable();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "locationInfo")
    public List<LocationDto> getLocationsInfo(){
        log.info("[GeneralInfoController] getLocationsInfo");
        return generalInfoService.getInfoForLocationsTable();
    }

}
