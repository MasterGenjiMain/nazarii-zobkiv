package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.Location;
import com.epam.spring.boot.cargodeliverysystem.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LocationRepositoryImpl implements LocationRepository {

    private final List<Location> locationList = new ArrayList<>();

    @Override
    public List<Location> getAllLocations() {
        return locationList;
    }

    @Override
    public Location getLocation(String locationName) {
        log.info("[Repository] getLocation by name {} ", locationName);
        return locationList.stream()
                .filter(l -> l.getLocationName().equals(locationName))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Location addLocation(Location location) {
        log.info("[Repository] addLocation by name {} ", location);
        locationList.add(location);
        return location;
    }

    @Override
    public Location updateLocation(String locationName, Location location) {
        log.info("[Repository] updateLocation by name {} ", locationName);
        boolean isDeleted = locationList.removeIf(l -> l.getLocationName().equals(locationName));
        if(isDeleted) {
            locationList.add(location);
        } else {
            throw new EntityNotFoundException();
        }
        return location;
    }

    @Override
    public boolean deleteLocation(String locationName) {
        log.info("[Repository] deleteLocation by name {} ", locationName);
        return locationList.removeIf(l -> l.getLocationName().equals(locationName));
    }
}
