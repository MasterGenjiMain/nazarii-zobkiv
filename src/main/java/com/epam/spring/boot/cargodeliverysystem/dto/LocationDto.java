package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class LocationDto {

    private long id;

    @NotBlank(message = "'locationName' shouldn't be empty", groups = OnCreate.class)
    private String locationName;

    @NotNull(message = "'cityId' shouldn't be empty", groups = OnCreate.class)
    private CityDto cityId;

    @NotNull(message = "'activeStatusId' shouldn't be empty", groups = OnCreate.class)
    private ActiveStatusDto activeStatusId;

}
