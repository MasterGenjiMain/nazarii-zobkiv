package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.LanguageDto;
import com.epam.spring.boot.cargodeliverysystem.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{langName}")
    public LanguageDto getLanguage(@PathVariable String langName) {
        log.info("[Controller] getLanguage by name {} ", langName);
        return languageService.getLanguage(langName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LanguageDto addLanguage(@RequestBody LanguageDto languageDto) {
        log.info("[Controller] addLanguage {} ", languageDto);
        return languageService.addLanguage(languageDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{langName}")
    public LanguageDto updateLanguage(@PathVariable String langName, @RequestBody LanguageDto languageDto) {
        log.info("[Controller] updateLanguage by name {} ", langName);
        return languageService.updateLanguage(langName, languageDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{langName}")
    public boolean deleteLanguage(@PathVariable String langName) {
        log.info("[Controller] deleteLanguage by name {} ", langName);
        return languageService.deleteLanguage(langName);
    }
}
