package com.epam.spring.boot.cargodeliverysystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryTypeDto {

    private long id;
    private String typeName;
    private long languageId;

}
