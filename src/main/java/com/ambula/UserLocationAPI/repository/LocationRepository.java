package com.ambula.UserLocationAPI.repository;

import com.ambula.UserLocationAPI.model.User;
import com.ambula.UserLocationAPI.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<UserLocation, Long> {

    @Query("SELECT l FROM UserLocation l WHERE sqrt((l.latitude - :lat)*(l.latitude - :lat) + (l.longitude - :lon)*(l.longitude - :lon)) < :dist")
    Iterable<UserLocation> findUserLocationsNearby(double lat, double lon, double dist);

    Iterable<UserLocation> findByUser(User user);

}

