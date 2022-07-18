package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.model.City;
import com.epam.spring.boot.cargodeliverysystem.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CityRepositoryImpl implements CityRepository {

    private final List<City> cityList = new ArrayList<>();

    @Override
    public City getCity(String name) {
        log.info("[Repository] getCity by name {} ", name);
        return cityList.stream()
                .filter(city -> city.getCityName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("City is not found!"));
    }

    @Override
    public City addCity(City city) {
        log.info("[Repository] addCity {} ", city);
        cityList.add(city);
        return city;
    }

    @Override
    public City updateCity(String name, City city) {
        log.info("[Repository] updateCity by name {} ", name);
        boolean isDeleted = cityList.removeIf(c -> c.getCityName().equals(name));
        if(isDeleted) {
            cityList.add(city);
        } else {
            throw new RuntimeException("City is not found!");
        }
        return city;
    }

    @Override
    public boolean deleteCity(String name) {
        log.info("[Repository] deleteCity by name {} ", name);
        return cityList.removeIf(c -> c.getCityName().equals(name));
    }
}
