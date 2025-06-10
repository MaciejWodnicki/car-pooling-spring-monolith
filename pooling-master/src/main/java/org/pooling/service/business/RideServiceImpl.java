package org.pooling.service.business;

import jakarta.transaction.Transactional;
import org.pooling.domain.business.Ride;
import org.pooling.repository.business.RideRepository;
import org.pooling.repository.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service("rideService")
public class RideServiceImpl implements RideService{

    private final RideRepository rideRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository, AppUserRepository appUserRepository) {
        this.rideRepository = rideRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Ride> listRides() {
        return rideRepository.findAll();
    }

    @Override
    public void addRide(Ride ride) {
        rideRepository.save(ride);
    }

    @Override
    public void editRide(Ride ride) {
        rideRepository.save(ride);
    }

    @Override
    public void removeRide(long id) {
        rideRepository.deleteById(id);
    }

    @Override
    public Ride getRide(long id) {
        return rideRepository.findById(id).get();
    }

    @Override
    public void addUserToRide(long rideId, long userId) {
        rideRepository.getReferenceById(rideId).addPassenger(appUserRepository.findById(userId));
    }

    @Override
    public void removeUserFromRide(long rideId, long userId) {
        rideRepository.getReferenceById(rideId).removePassenger(appUserRepository.findById(userId));
        System.out.println("removed passenger2");

    }
}
