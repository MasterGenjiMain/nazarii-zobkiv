package com.epam.spring.boot.cargodeliverysystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptStatusDto {

    private long id;
    private String statusName;

}
