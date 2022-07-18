package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
    private long id;
    private String locationName;
    private long cityId;
    private int activeStatusId;
}
