package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptStatus {
    private long id;
    private String statusName;
}
