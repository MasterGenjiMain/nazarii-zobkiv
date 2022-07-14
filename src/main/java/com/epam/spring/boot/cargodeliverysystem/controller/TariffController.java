package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tariffs")
@RequiredArgsConstructor
public class TariffController {

    private final TariffService tariffService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{tariffName}")
    public TariffDto getTariff(@PathVariable String tariffName) {
        log.info("[Controller] getTariff by name {} ", tariffName);
        return tariffService.getTariff(tariffName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TariffDto addTariff(@RequestBody TariffDto tariffDto) {
        log.info("[Controller] addTariff {} ", tariffDto);
        return tariffService.addTariff(tariffDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{tariffName}")
    public TariffDto updateTariff(@PathVariable String tariffName, @RequestBody TariffDto tariffDto) {
        log.info("[Controller] updateTariff {} by name ", tariffName);
        return tariffService.updateTariff(tariffName, tariffDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{tariffName}")
    public boolean deleteUser(@PathVariable String tariffName) {
        log.info("[Controller] deleteUser {} by name ", tariffName);
        return tariffService.deleteTariff(tariffName);
    }
}
