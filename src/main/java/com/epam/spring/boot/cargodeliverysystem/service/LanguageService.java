package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.LanguageDto;

public interface LanguageService {

    LanguageDto getLanguage(String languageName);

    LanguageDto addLanguage(LanguageDto languageDto);

    LanguageDto updateLanguage(String languageName, LanguageDto languageDto);

    boolean deleteLanguage(String languageName);
}
