package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class DeliveryOrderDto {

    private long id;

    @NotNull(message = "'locationFromId' shouldn't be empty", groups = OnCreate.class)
    private LocationDto locationFromId;

    @NotNull(message = "'locationToId' shouldn't be empty", groups = OnCreate.class)
    private LocationDto locationToId;

    @NotBlank(message = "'cargoName' shouldn't be empty", groups = OnCreate.class)
    private String cargoName;

    private String cargoDescription;

    @NotBlank(message = "'address' shouldn't be empty", groups = OnCreate.class)
    private String address;

    @NotNull(message = "'deliveryTypeId' shouldn't be empty", groups = OnCreate.class)
    private DeliveryTypeDto deliveryTypeId;

    @NotNull(message = "'weight' shouldn't be empty", groups = OnCreate.class)
    private double weight;

    @NotNull(message = "'volume' shouldn't be empty", groups = OnCreate.class)
    private double volume;

    private Timestamp receivingDate;

    @NotNull(message = "'tariffId' shouldn't be empty", groups = OnCreate.class)
    private TariffDto tariffId;

}
