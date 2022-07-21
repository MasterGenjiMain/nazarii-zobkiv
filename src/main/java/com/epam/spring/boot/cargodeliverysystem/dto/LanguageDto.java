package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class LanguageDto {

    private long id;

    @NotBlank(message = "'languageName' shouldn't be empty", groups = OnCreate.class)
    private String languageName;

}
