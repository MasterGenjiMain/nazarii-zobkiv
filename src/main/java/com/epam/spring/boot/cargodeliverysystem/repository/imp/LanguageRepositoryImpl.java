package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.Language;
import com.epam.spring.boot.cargodeliverysystem.repository.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LanguageRepositoryImpl implements LanguageRepository {

    private final List<Language> languageList = new ArrayList<>();

    @Override
    public Language getLanguage(String languageName) {
        log.info("[Repository] getLanguage by name {} ", languageName);
        return languageList.stream()
                .filter(lang -> lang.getLanguageName().equals(languageName))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Language addLanguage(Language language) {
        log.info("[Repository] addLanguage {} ", language);
        languageList.add(language);
        return language;
    }

    @Override
    public Language updateLanguage(String languageName, Language language) {
        log.info("[Repository] updateLanguage by name {} ", languageName);
        boolean isDeleted = languageList.removeIf(lang -> lang.getLanguageName().equals(languageName));
        if(isDeleted) {
            languageList.add(language);
        } else {
            throw new EntityNotFoundException();
        }
        return language;
    }

    @Override
    public boolean deleteDeliveryOrder(String languageName) {
        log.info("[Repository] deleteDeliveryOrder by name {} ", languageName);
        return languageList.removeIf(lang -> lang.getLanguageName().equals(languageName));
    }
}
