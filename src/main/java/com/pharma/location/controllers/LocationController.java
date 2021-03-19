package com.pharma.location.controllers;

import com.pharma.location.models.LocationDto;
import com.pharma.location.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveLocation(@RequestBody LocationDto location) {
        return ResponseEntity.ok(locationService.save(location));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getLocation(@RequestBody Long locationId) {
        return ResponseEntity.ok(locationService.getById(locationId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateLocation(@RequestBody LocationDto location) {
        return ResponseEntity.ok(locationService.update(location));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteLocation(@RequestBody LocationDto location) {
        locationService.delete(location);
    }
}
