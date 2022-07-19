package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.LanguageDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.LanguageMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Language;
import com.epam.spring.boot.cargodeliverysystem.repository.LanguageRepository;
import com.epam.spring.boot.cargodeliverysystem.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public LanguageDto getLanguage(String languageName) {
        log.info("getLanguage by name {}", languageName);
        Language language = languageRepository.getLanguage(languageName);
        return languageMapper.mapLanguageToLanguageDto(language);
    }

    @Override
    public LanguageDto addLanguage(LanguageDto languageDto) {
        log.info("addLanguage with name {}", languageDto.getLanguageName());
        Language language = languageMapper.mapLanguageDtoToLanguage(languageDto);
        language = languageRepository.addLanguage(language);
        return languageMapper.mapLanguageToLanguageDto(language);
    }

    @Override
    public LanguageDto updateLanguage(String languageName, LanguageDto languageDto) {
        log.info("updateLanguage with name {}", languageName);
        Language language = languageMapper.mapLanguageDtoToLanguage(languageDto);

        Language oldLanguage = languageRepository.getLanguage(languageName);
        language.setId(oldLanguage.getId());

        language = languageRepository.updateLanguage(languageName, language);
        return languageMapper.mapLanguageToLanguageDto(language);
    }

    @Override
    public boolean deleteLanguage(String languageName) {
        log.info("deleteDeliveryOrder with name {}", languageName);
        return languageRepository.deleteDeliveryOrder(languageName);
    }
}
