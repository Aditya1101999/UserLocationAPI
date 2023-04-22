package com.ambula.UserLocationAPI.service;

import com.ambula.UserLocationAPI.model.User;
import com.ambula.UserLocationAPI.model.UserLocation;

public interface UserLocationService {

    UserLocation createUserLocation(UserLocation userLocation);

    UserLocation updateUserLocation(Long id, UserLocation userLocation);

    Iterable<UserLocation> getAllUserLocations();

    UserLocation getUserLocationById(Long id);

    boolean deleteUserLocation(Long id);

    Iterable<UserLocation> getUserLocationsByUser(User user);

    Iterable<UserLocation> getNearestLocations(double lat, double lon, double dist);


}


