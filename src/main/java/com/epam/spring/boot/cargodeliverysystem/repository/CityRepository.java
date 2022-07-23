package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.City;

public interface CityRepository {

    City getCity(String name);

    City addCity(City city);

    City updateCity(String name, City city);

    boolean deleteCity(String name);
}
