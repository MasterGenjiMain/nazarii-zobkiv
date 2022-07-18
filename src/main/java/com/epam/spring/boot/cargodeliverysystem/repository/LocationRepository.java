package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Location;

import java.util.List;

public interface LocationRepository {

    List<Location> getAllLocations();

    Location getLocation(String locationName);

    Location addLocation(Location location);

    Location updateLocation(String locationName, Location location);

    boolean deleteLocation(String locationName);
}
