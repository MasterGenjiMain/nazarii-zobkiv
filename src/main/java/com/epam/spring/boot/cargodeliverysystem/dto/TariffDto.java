package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class TariffDto {

    @Null(message = "'id' should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "'id' shouldn't be empty", groups = OnCreate.class)
    private long id;

    @NotBlank(message = "'tariffName' shouldn't be empty", groups = OnCreate.class)
    private String tariffName;

    @NotNull(message = "'tariffPrice' shouldn't be empty", groups = OnCreate.class)
    private double tariffPrice;

    @NotBlank(message = "'tariffInfo' shouldn't be empty", groups = OnCreate.class)
    private String tariffInfo;

    @NotNull(message = "'languageId' shouldn't be empty", groups = OnCreate.class)
    private long languageId;

}
