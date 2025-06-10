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
import org.pooling.service.user.EmailService;
import org.pooling.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private ReCaptchaService reCaptchaService;
    @Autowired
    private EmailService emailService;

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

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    @Transactional
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser,
                             BindingResult result,
                             Model model,
                             HttpServletRequest request) {

        System.out.println("First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() +
                " Tel.: " + appUser.getTelephone() +
                " Email: " + appUser.getEmail());

        Address addr = appUser.getAddress();
        if (addr != null) {
            System.out.println("Address -> Street: " + addr.getStreet() +
                    ", City: " + addr.getCity() +
                    ", State: " + addr.getState() +
                    ", ZIP: " + addr.getZip() +
                    ", Country: " + addr.getCountry());
        }

        // 1. Save the address first (if it doesn't exist)
        Address existingAddress = addressService.getAddress(addr.getId());
        if (existingAddress != null) {
            appUser.setAddress(existingAddress);
        } else {
            addressService.addAddress(addr);
        }

        // 2. Set default ROLE_USER (ID=0) but check if user already exists
        AppUser existingUser = appUserService.findByLogin(appUser.getLogin());
        if (existingUser != null) {
            // Handle case where user already exists
            model.addAttribute("error", "User with this login already exists");
            return "register"; // Return to registration page with error
        }

        // 3. Create new user with default role
        Set<AppUserRole> roles = new HashSet<>();
        AppUserRole userRole = appUserRoleService.getAppUserRole(0);
        roles.add(userRole);
        appUser.setAppUserRole(roles);

        // 4. Save the new user
        appUserService.addAppUser(appUser);

        // 5. Clear any persistence context to prevent caching issues
        entityManager.flush();
        entityManager.clear();

        return "availableRides";
    }

    @RequestMapping("/delete/{appUserId}")
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

}









