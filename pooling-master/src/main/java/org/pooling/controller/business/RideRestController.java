package org.pooling.controller.business;

import jakarta.persistence.EntityNotFoundException;
import org.pooling.domain.business.Ride;
import org.pooling.repository.business.RideRepository;
import org.pooling.service.business.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rideRest")
public class RideRestController {
    private RideRepository rideRepository;

    @Autowired
    public RideRestController(RideRepository rideRepository) {this.rideRepository = rideRepository;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Ride getRideInJSON(@PathVariable("id") long id) {
        return rideRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @RequestMapping(value = "/{id}.xml", method = RequestMethod.GET, produces = "application/xml")
    public Ride getRideInXML(@PathVariable("id") long id) {
        return rideRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
