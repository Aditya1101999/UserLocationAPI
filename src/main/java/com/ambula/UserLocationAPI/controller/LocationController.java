package com.ambula.UserLocationAPI.controller;

import com.ambula.UserLocationAPI.model.UserLocation;
import com.ambula.UserLocationAPI.service.impl.LocationServiceImpl ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationServiceImpl locationService;

    @PostMapping("/create")
    public ResponseEntity<UserLocation> createLocation(@RequestBody UserLocation location) {
        locationService.createUserLocation(location);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserLocation> updateLocation(@PathVariable("id") Long id, @RequestBody UserLocation location) {
        locationService.updateUserLocation(id, location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/nearest")
    public ResponseEntity<Iterable<UserLocation>> getNearestLocations(@RequestParam("lat") double lat,
                                                                       @RequestParam("lon") double lon,
                                                                       @RequestParam("dist") double dist) {
        Iterable<UserLocation> locations = locationService.getNearestLocations(lat, lon, dist);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    

}

