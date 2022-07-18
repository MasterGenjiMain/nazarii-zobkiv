package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.LanguageDto;
import com.epam.spring.boot.cargodeliverysystem.model.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageDto mapLanguageToLanguageDto(Language language);

    Language mapLanguageDtoToLanguage(LanguageDto languageDto);
}
