package org.pooling.service.business;

import org.pooling.domain.business.Ride;

import java.util.List;

public interface RideService {

    List<Ride> getAvailableRides();
}
