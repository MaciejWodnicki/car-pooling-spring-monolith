package org.pooling.controller.business;

import org.pooling.domain.business.Ride;
import org.pooling.service.business.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RideController {

    private final RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/availableRides")
    public String showAvailableRides(Model model) {
        List<Ride> rides = rideService.getAvailableRides();
        model.addAttribute("ridesList", rides);
        return "availableRides";
    }
}
