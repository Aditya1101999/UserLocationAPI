package com.ambula.UserLocationAPI.service.impl;

import com.ambula.UserLocationAPI.model.User;
import com.ambula.UserLocationAPI.model.UserLocation;
import com.ambula.UserLocationAPI.repository.LocationRepository;
import com.ambula.UserLocationAPI.repository.UserRepository;
import com.ambula.UserLocationAPI.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements UserLocationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public UserLocation createUserLocation(UserLocation userLocation) {
        return locationRepository.save(userLocation);
    }

    @Override
    public UserLocation updateUserLocation(Long id, UserLocation userLocation) {
        userLocation.setId(id);
        return locationRepository.save(userLocation);
    }

    @Override
    public Iterable<UserLocation> getAllUserLocations() {
        return locationRepository.findAll();
    }

    @Override
    public UserLocation getUserLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteUserLocation(Long id) {
        try {
            locationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Iterable<UserLocation> getUserLocationsByUser(User user) {
        return locationRepository.findByUser(user);
    }

    @Override
    public Iterable<UserLocation> getNearestLocations(double lat, double lon, double dist) {
        return locationRepository.findUserLocationsNearby(lat, lon, dist);
    }

}
