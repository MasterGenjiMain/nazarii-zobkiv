package com.epam.spring.boot.cargodeliverysystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {

    private long id;
    private String locationName;
    private long cityId;
    private int activeStatusId;

}
