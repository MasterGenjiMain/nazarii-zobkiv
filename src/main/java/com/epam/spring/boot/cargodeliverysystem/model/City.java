package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {
    private long id;
    private String cityName;
}
