package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.service.GeneralInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/generalInfo")
@RequiredArgsConstructor
public class GeneralInfoController {

    private final GeneralInfoService generalInfoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "tariffInfo/{languageName}")
    public List<TariffDto> getTariffsInfo(@PathVariable String languageName){
        log.info("[GeneralInfoController] getTariffsInfo");
        return generalInfoService.getInfoForTariffTableWithName(languageName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "locationInfo")
    public List<LocationDto> getLocationsInfo(){
        log.info("[GeneralInfoController] getLocationsInfo");
        return generalInfoService.getInfoForLocationTable();
    }

}
