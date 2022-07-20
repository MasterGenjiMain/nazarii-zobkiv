package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ReceiptDto {

    @NotNull(message = "'id' shouldn't be empty", groups = OnCreate.class)
    private long id;

    @Null(message = "'userId' should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "'userId' shouldn't be empty", groups = OnCreate.class)
    private long userId;

    @NotNull(message = "'managerId' shouldn't be empty", groups = OnCreate.class)
    private long managerId;

    @NotNull(message = "'price' shouldn't be empty", groups = OnCreate.class)
    private double price;

    @NotNull(message = "'receiptStatusId' shouldn't be empty", groups = OnCreate.class)
    private long receiptStatusId;

    @Null(message = "'deliveryOrderId' should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "'deliveryOrderId' shouldn't be empty", groups = OnCreate.class)
    private long deliveryOrderId;

}
