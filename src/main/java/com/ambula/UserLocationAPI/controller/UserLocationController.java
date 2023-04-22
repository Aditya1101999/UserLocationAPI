package com.ambula.UserLocationAPI.controller;

import com.ambula.UserLocationAPI.model.UserLocation;
import com.ambula.UserLocationAPI.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-locations")
public class UserLocationController {

    @Autowired
    private UserLocationService userLocationService;

    // Create a new user location
    @PostMapping("")
    public ResponseEntity<UserLocation> createUserLocation(@RequestBody UserLocation userLocation) {
        UserLocation newUserLocation = userLocationService.createUserLocation(userLocation);
        return new ResponseEntity<>(newUserLocation, HttpStatus.CREATED);
    }

    // Get all user locations
    @GetMapping("")
    public ResponseEntity<List<UserLocation>> getAllUserLocations() {
        List<UserLocation> userLocations = (List<UserLocation>) userLocationService.getAllUserLocations();
        return new ResponseEntity<>(userLocations, HttpStatus.OK);
    }

    // Get a user location by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserLocation> getUserLocationById(@PathVariable("id") Long id) {
        UserLocation userLocation = userLocationService.getUserLocationById(id);
        if (userLocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userLocation, HttpStatus.OK);
    }

    // Update a user location
    @PutMapping("/{id}")
    public ResponseEntity<UserLocation> updateUserLocation(@PathVariable("id") Long id, @RequestBody UserLocation userLocation) {
        UserLocation updatedUserLocation = userLocationService.updateUserLocation(id, userLocation);
        if (updatedUserLocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUserLocation, HttpStatus.OK);
    }

    // Delete a user location
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLocation(@PathVariable("id") Long id) {
        boolean deleted = userLocationService.deleteUserLocation(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

