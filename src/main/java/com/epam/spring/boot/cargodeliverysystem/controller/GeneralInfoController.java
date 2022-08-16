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
    public List<TariffDto> getTariffsInfo(@PathVariable String languageName,
                                          @RequestParam(defaultValue = "0") int pageNum,
                                          @RequestParam(required = false) String sortBy){
        log.info("[GeneralInfoController] getTariffsInfo");
        return generalInfoService.getInfoAboutTariffsWithLanguageName(languageName, pageNum, sortBy);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "locationInfo")
    public List<LocationDto> getLocationsInfo(@RequestParam(defaultValue = "0") int pageNum,
                                              @RequestParam(required = false) String sortBy){
        log.info("[GeneralInfoController] getLocationsInfo");
        return generalInfoService.getInfoAboutLocations(pageNum, sortBy);
    }

}
