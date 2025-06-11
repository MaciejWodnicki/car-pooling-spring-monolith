package org.pooling.service.business;

import org.pooling.domain.business.Ride;

import java.util.List;

public interface RideService {

    List<Ride> listRides();
    void addRide(Ride ride);
    void editRide(Ride ride);
    void removeRide(long id);
    Ride getRide(long id);
    Boolean addUserToRide(long rideId, long userId);
    void removeUserFromRide(long rideId, long userId);
}
