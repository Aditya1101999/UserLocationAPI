package com.ambula.UserLocationAPI.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ambula.UserLocationAPI.model.User;
import com.ambula.UserLocationAPI.service.impl.LocationServiceImpl;
import com.ambula.UserLocationAPI.model.UserLocation;
import com.ambula.UserLocationAPI.repository.UserRepository;
import com.ambula.UserLocationAPI.repository.LocationRepository;

import org.springframework.boot.test.context.SpringBootTest; // annotation for spring boot test


@SpringBootTest // annotation for spring boot test
public class UserLocationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl userLocationService;

    private User testUser;
    private UserLocation testLocation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");

        testLocation = new UserLocation();
        testLocation.setId(1L);
        testLocation.setUser(testUser);
        testLocation.setLatitude(37.7749);
        testLocation.setLongitude(-122.4194);
    }

    @Test
    void testCreateUserLocation() {
        when(locationRepository.save(any(UserLocation.class))).thenReturn(testLocation);

        UserLocation createdLocation = userLocationService.createUserLocation(testLocation);

        assertThat(createdLocation).isEqualTo(testLocation);
    }

    @Test
    void testUpdateUserLocation() {
        when(locationRepository.save(any(UserLocation.class))).thenReturn(testLocation);
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(testLocation));

        UserLocation updatedLocation = userLocationService.updateUserLocation(1L, testLocation);

        assertThat(updatedLocation).isEqualTo(testLocation);
    }

    @Test
    void testGetAllUserLocations() {
        List<UserLocation> locations = new ArrayList<>();
        locations.add(testLocation);

        when(locationRepository.findAll()).thenReturn(locations);

        Iterable<UserLocation> allLocations = userLocationService.getAllUserLocations();

        assertThat(allLocations).containsExactly(testLocation);
    }

    @Test
    void testGetUserLocationById() {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(testLocation));

        UserLocation foundLocation = userLocationService.getUserLocationById(1L);

        assertThat(foundLocation).isEqualTo(testLocation);
    }

    @Test
    void testDeleteUserLocation() {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(testLocation));

        boolean isDeleted = userLocationService.deleteUserLocation(1L);

        assertThat(isDeleted).isTrue();
    }

    @Test
    void testGetUserLocationsByUser() {
        List<UserLocation> locations = new ArrayList<>();
        locations.add(testLocation);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(locationRepository.findByUser(any(User.class))).thenReturn(locations);

        Iterable<UserLocation> userLocations = userLocationService.getUserLocationsByUser(testUser);

        assertThat(userLocations).containsExactly(testLocation);
    }
    @Test
    void testGetNearestLocations() {
        List<UserLocation> locations = new ArrayList<>();
        locations.add(testLocation);

        when(locationRepository.findUserLocationsNearby(anyDouble(), anyDouble(), anyDouble())).thenReturn(locations);

        Iterable<UserLocation> nearestLocations = userLocationService.getNearestLocations(37.7749, -122.4194, 10);

        assertThat(nearestLocations).containsExactly(testLocation);
    }

}

