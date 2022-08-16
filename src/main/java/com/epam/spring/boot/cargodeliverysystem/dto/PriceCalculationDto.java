package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceCalculationDto {

    @Positive(message = "'volume' should be positive", groups = OnCreate.class)
    private double volume;

    @Positive(message = "'distance' should be positive", groups = OnCreate.class)
    private double distance;

    @Positive(message = "'weight' should be positive", groups = OnCreate.class)
    private double weight;

    @NotBlank(message = "'tariffName' shouldn't be empty", groups = OnCreate.class)
    private String tariffName;

    @NegativeOrZero(message = "'price' should be positive", groups = OnCreate.class)
    private double calculatedPrice;

}
