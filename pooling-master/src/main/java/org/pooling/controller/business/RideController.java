package org.pooling.controller.business;

import com.sun.mail.iap.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.pooling.domain.business.Ride;
import org.pooling.service.PdfService;
import org.pooling.service.business.RideService;
import org.pooling.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller()
public class RideController {

    private final RideService rideService;
    private final AppUserService appUserService;
    private final PdfService pdfService;

    @Autowired
    public RideController(RideService rideService, AppUserService appUserService, PdfService pdfService) {
        this.rideService = rideService;
        this.appUserService = appUserService;
        this.pdfService = pdfService;
    }

    @GetMapping("/availableRides")
    public String showAvailableRides(Model model, Principal principal) {
        List<Ride> rides = rideService.listRides();
        model.addAttribute("ridesList", rides);
        model.addAttribute("currentUser", principal.getName());
        return "availableRides";
    }

    @GetMapping("/availableRides/{id}")
    public String getRide(@PathVariable("id") int id, Model model) {
        Ride ride = rideService.getRide(id);
        model.addAttribute("ride", ride);
        return "rideDetails";
    }

    @PostMapping("/availableRides")
    public String addRide(Ride ride, Model model) {
        rideService.addRide(ride);
        return "redirect:/availableRides";
    }

    @PutMapping("/availableRides")
    public void updateRide(Ride ride, Model model) {
        rideService.editRide(ride);
    }

    @DeleteMapping("/availableRides/{id}")
    public String removeRide(@PathVariable("id")long id) {
        rideService.removeRide(id);
        return "redirect:/availableRides";
    }

    @PostMapping("/availableRides/{id}/passengers")
    public String addPassenger(@PathVariable("id") long id, Principal principal, HttpServletResponse response) {
        Boolean result = rideService.addUserToRide(id, appUserService.findByLogin(principal.getName()).getId());

        if(result){
            System.out.println("added passenger1");
            pdfService.generatePdf(rideService.getRide(id), response);
        }

        return "redirect:/availableRides";
    }

    @DeleteMapping("/availableRides/{id}/passengers")
    public String removePassenger(@PathVariable("id") long id, Principal principal) {
        rideService.removeUserFromRide(id, appUserService.findByLogin(principal.getName()).getId());
        System.out.println("removed passenger1");
        return "redirect:/availableRides";
    }

}
