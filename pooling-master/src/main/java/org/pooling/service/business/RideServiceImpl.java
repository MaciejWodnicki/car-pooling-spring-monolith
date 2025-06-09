package org.pooling.service.business;

import org.pooling.domain.business.Ride;
import org.pooling.repository.business.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideServiceImpl implements RideService{

    private final RideRepository rideRepository;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public List<Ride> getAvailableRides() {
        return rideRepository.findAllAvailableRides();
    }
}
