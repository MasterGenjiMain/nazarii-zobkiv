package com.epam.spring.boot.cargodeliverysystem.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class DeliveryOrderDto {

    private long id;
    private long locationFromId;
    private long locationToId;
    private String cargoName;
    private String cargoDescription;
    private String address;
    private long deliveryTypeId;
    private double weight;
    private double volume;
    private Timestamp receivingDate;
    private long tariffId;

}
