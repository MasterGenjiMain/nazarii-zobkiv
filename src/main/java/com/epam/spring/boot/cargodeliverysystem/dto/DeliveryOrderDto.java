package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class DeliveryOrderDto {

    private long id;

    @NotNull(message = "'locationFrom' shouldn't be empty", groups = OnCreate.class)
    private LocationDto locationFrom;

    @NotNull(message = "'locationTo' shouldn't be empty", groups = OnCreate.class)
    private LocationDto locationTo;

    @NotBlank(message = "'cargoName' shouldn't be empty", groups = OnCreate.class)
    private String cargoName;

    private String cargoDescription;

    @NotBlank(message = "'address' shouldn't be empty", groups = OnCreate.class)
    private String address;

    @NotNull(message = "'deliveryType' shouldn't be empty", groups = OnCreate.class)
    private DeliveryTypeDto deliveryType;

    @NotNull(message = "'weight' shouldn't be empty", groups = OnCreate.class)
    @Positive
    private double weight;

    @NotNull(message = "'volume' shouldn't be empty", groups = OnCreate.class)
    @Positive
    private double volume;

    private Timestamp receivingDate;

    @NotNull(message = "'tariff' shouldn't be empty", groups = OnCreate.class)
    private TariffDto tariff;

    @NotNull(message = "'distance' shouldn't be empty", groups = OnCreate.class)
    @Positive
    private double distance;

}
