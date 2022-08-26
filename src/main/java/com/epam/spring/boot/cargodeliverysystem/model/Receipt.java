package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Receipt {
    private long id;
    private long userId;
    private long managerId;
    private double price;
    private long receiptStatusId;
    private long deliveryOrderId;
}
