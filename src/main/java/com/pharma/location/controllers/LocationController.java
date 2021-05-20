package com.pharma.location.controllers;

import com.pharma.location.models.Location;
import com.pharma.location.models.LocationDto;
import com.pharma.location.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping( value = "/all", produces = "application/json")
    public ResponseEntity<?> getAllLocations() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getLocation(@PathVariable("id") long id) {
        return ResponseEntity.ok(locationService.getById(id));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> saveLocation(LocationDto location) {
        Location loc;
        try{
            loc = new Location(location);
        } catch (NullPointerException exception){
            return new ResponseEntity<>("Failed to create location", HttpStatus.BAD_REQUEST);
        }
        loc = locationService.save(loc);
        if (loc == null) {
            return new ResponseEntity<>("Failed to create location", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loc, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<?> updateLocation(LocationDto location) {
        Location loc;
        try{
            loc = new Location(location);
        } catch (NullPointerException exception){
            return new ResponseEntity<>("Failed to update location", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(locationService.save(loc), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable("id") long id) {
        locationService.delete(id);
        return new ResponseEntity<>("Location deleted", HttpStatus.OK);
    }
}
