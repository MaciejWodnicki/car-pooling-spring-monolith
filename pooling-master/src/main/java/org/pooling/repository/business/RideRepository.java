package org.pooling.repository.business;

import org.pooling.domain.business.Ride;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RideRepository {

    // Dummy data for now. Replace with database interaction (JPA or JDBC).
    public List<Ride> findAllAvailableRides() {
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(1, "Alice Johnson", "New York", "Boston", LocalDateTime.now().plusDays(1), 3));
        rides.add(new Ride(2, "Bob Smith", "Los Angeles", "San Diego", LocalDateTime.now().plusHours(5), 2));
        rides.add(new Ride(3, "Carla Martinez", "Chicago", "Milwaukee", LocalDateTime.now().plusDays(2), 4));
        return rides;
    }
}