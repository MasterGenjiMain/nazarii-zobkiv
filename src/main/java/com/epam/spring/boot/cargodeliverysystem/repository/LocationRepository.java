package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Location;

public interface LocationRepository {

    Location getLocation(String locationName);

    Location addLocation(Location location);

    Location updateLocation(String locationName, Location location);

    boolean deleteLocation(String locationName);
}
