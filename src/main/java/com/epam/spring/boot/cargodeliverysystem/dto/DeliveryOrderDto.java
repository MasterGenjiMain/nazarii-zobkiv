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
import java.sql.Timestamp;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class DeliveryOrderDto {

    @Null(message = "'id' should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "'id' shouldn't be empty", groups = OnCreate.class)
    private long id;

    @NotNull(message = "'locationFromId' shouldn't be empty", groups = OnCreate.class)
    private long locationFromId;

    @NotNull(message = "'locationToId' shouldn't be empty", groups = OnCreate.class)
    private long locationToId;

    @NotBlank(message = "'cargoName' shouldn't be empty", groups = OnCreate.class)
    private String cargoName;

    private String cargoDescription;

    @NotBlank(message = "'address' shouldn't be empty", groups = OnCreate.class)
    private String address;

    @NotNull(message = "'deliveryTypeId' shouldn't be empty", groups = OnCreate.class)
    private long deliveryTypeId;

    @NotNull(message = "'weight' shouldn't be empty", groups = OnCreate.class)
    private double weight;

    @NotNull(message = "'volume' shouldn't be empty", groups = OnCreate.class)
    private double volume;

    @Null(message = "'receivingDate' should be absent in request", groups = OnCreate.class)
    private Timestamp receivingDate;

    @NotNull(message = "'tariffId' shouldn't be empty", groups = OnCreate.class)
    private long tariffId;

}
