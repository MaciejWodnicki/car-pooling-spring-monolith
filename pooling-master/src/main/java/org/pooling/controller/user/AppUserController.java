package org.pooling.controller.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.pooling.domain.user.Address;
import org.pooling.domain.user.AppUser;
import org.pooling.domain.user.AppUserRole;
import org.pooling.service.*;
import org.pooling.service.user.AddressService;
import org.pooling.service.user.AppUserRoleService;
import org.pooling.service.user.AppUserService;
import org.pooling.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AppUserController {

    private AppUserValidator appUserValidator = new AppUserValidator();

    // injected by field
    @Autowired
    AddressService addressService;

    // injected by field
    @Autowired
    AppUserRoleService appUserRoleService;

    // injected by constructor
    private AppUserService appUserService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {
        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId" , -1);
        if (appUserId > 0) {
            AppUser appUser = appUserService.getAppUser(appUserId);
            appUser.setPassword("");

            // SAFE NAVIGATION - Handle null address
            if(appUser.getAddress() != null) {
                Address address = addressService.getAddress(appUser.getAddress().getId());
                appUser.setAddress(address);
                model.addAttribute("selectedAddress", address.getId());
            }

            model.addAttribute("appUser", appUser);
        } else {
            model.addAttribute("appUser", new AppUser());
        }

        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("appUserRoleList",appUserRoleService.listAppUserRoles());
        model.addAttribute("addressesList", addressService.listAddresses());

        return "appUser";
    }


    @DeleteMapping("/appUsers/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers";
    }

    @RequestMapping(value = "/managerList")
    public String showManagers(Model model) {
        // Get all users with MANAGER role
        Set<AppUser> managers = appUserService.getUsersByRole("MANAGER");
        model.addAttribute("managerList", managers);
        return "managerList"; // This will resolve to managerList.jsp
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    @Transactional
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser,
                             BindingResult result,
                             Model model,
                             HttpServletRequest request) {

        // Check for existing user
        if (appUserService.findByLogin(appUser.getLogin()) != null) {
            model.addAttribute("error", "User with this login already exists");
            return "register";
        }

        Address address = appUser.getAddress();
        if (address != null) {
            Address existingAddress = addressService.getAddress(address.getId());
            if (existingAddress != null) {
                appUser.setAddress(existingAddress);
            } else {
                addressService.addAddress(address);
            }
        }

        //Generate confirmation token tied to email
        String token = tokenService.generateToken(appUser.getEmail());
        System.out.println("token: " + token);
        // Store appUser temporarily (in tokenService or a cache/database)
        tokenService.storePendingUser(token, appUser); // You must implement this

        // Send email
        String confirmationLink = "http://localhost:8080/confirm?token=" + token;
        emailService.sendEmail(appUser.getEmail(), confirmationLink, "App User Registration");

        return "registrationSuccess";
    }

    @GetMapping("/confirm")
    @Transactional
    public String confirmRegistration(@RequestParam("token") String token) {
        if (!tokenService.validateToken(token)) {
            return "main"; // Invalid token
        }

        AppUser appUser = tokenService.getPendingUser(token); // Retrieve stored AppUser
        if (appUser == null) {
            return "main"; // User not found in temporary store
        }

        // Assign default role
        Set<AppUserRole> roles = new HashSet<>();
        AppUserRole userRole = appUserRoleService.getAppUserRole(0); // ROLE_USER
        roles.add(userRole);
        appUser.setAppUserRole(roles);

        System.out.println("role:"+appUser.getAppUserRole().toString());
        appUserService.addAppUser(appUser); // Now save to database
        tokenService.removeToken(token);    // Clean up after confirmation

        return "registrationSuccess";
    }

}









