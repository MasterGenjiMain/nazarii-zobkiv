package com.epam.spring.boot.cargodeliverysystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptDto {

    private long id;
    private long userId;
    private long managerId;
    private double price;
    private long receiptStatusId;
    private long deliveryOrderId;

}
