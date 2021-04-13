package com.pharma.location.services;

import com.pharma.location.models.Location;
import com.pharma.location.models.LocationDto;
import com.pharma.location.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location save(Location location){
        return locationRepository.save(location);
    }

    public Optional<Location> getById(Long locationId){
        return locationRepository.findById(locationId);
    }

    public Location update(LocationDto location){
        Location loc = new Location(location);
        return locationRepository.save(loc);
    }

    public void delete(LocationDto location){
        Location loc = new Location(location);
        locationRepository.delete(loc);
    }
}
