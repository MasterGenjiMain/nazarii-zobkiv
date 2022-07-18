package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Language;

public interface LanguageRepository {

    Language getLanguage(String languageName);

    Language addLanguage(Language language);

    Language updateLanguage(String languageName, Language language);

    boolean deleteDeliveryOrder(String languageName);
}
