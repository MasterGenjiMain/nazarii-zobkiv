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

    @NotNull(message = "'user' shouldn't be empty", groups = OnCreate.class)
    private UserDto user;

    @NotNull(message = "'manager' shouldn't be empty", groups = OnCreate.class)
    private UserDto manager;

    private double price;

    @NotNull(message = "'receiptStatus' shouldn't be empty", groups = OnCreate.class)
    private ReceiptStatusDto receiptStatus;

    @NotNull(message = "'deliveryOrder' shouldn't be empty", groups = OnCreate.class)
    private DeliveryOrderDto deliveryOrder;

}
