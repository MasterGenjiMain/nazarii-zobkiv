package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ReceiptDto {

    private long id;

    @NotNull(message = "'userId' shouldn't be empty", groups = OnCreate.class)
    private long userId;

    @NotNull(message = "'managerId' shouldn't be empty", groups = OnCreate.class)
    private long managerId;

    @NotNull(message = "'price' shouldn't be empty", groups = OnCreate.class)
    private double price;

    @NotNull(message = "'receiptStatusId' shouldn't be empty", groups = OnCreate.class)
    private long receiptStatusId;

    @NotNull(message = "'deliveryOrderId' shouldn't be empty", groups = OnCreate.class)
    private long deliveryOrderId;

}
