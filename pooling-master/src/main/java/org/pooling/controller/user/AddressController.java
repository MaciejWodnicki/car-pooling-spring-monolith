package org.pooling.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.pooling.domain.user.Address;
import org.pooling.service.user.AddressService;
import org.pooling.validator.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressController {
    private AddressValidator addressValidator = new AddressValidator();

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping("/addresses")
    public String showAddresses(Model model, HttpServletRequest request) {
        int addressId = ServletRequestUtils.getIntParameter(request, "addressId" , -1);
        if (addressId > 0){
            model.addAttribute("address", addressService.getAddress(addressId));
        }
        else
            model.addAttribute("address", new Address());
        model.addAttribute("addressList", addressService.listAddresses());
        return "address";
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@Valid @ModelAttribute("address") Address address, BindingResult result, Model model) {
        System.out.println("Street: " + address.getStreet() +
                ", City: " + address.getCity() +
                ", State: " + address.getState() +
                ", ZIP: " + address.getZip() +
                ", Country: " + address.getCountry());
        System.out.println("Address ID: " + address.getId());

        addressValidator.validate(address, result);

        if (!result.hasErrors()) {

            if (address.getId() <= 0)
                addressService.addAddress(address);
            else
                addressService.editAddress(address);

            return "redirect:addresses";
        }

        model.addAttribute("addressList", addressService.listAddresses());
        return "address";

    }

    @RequestMapping("/addresses/delete/{addressId}")
    public String deleteUser(@PathVariable("addressId") Long addressId) {
        addressService.removeAddress(addressId);
        return "redirect:/addresses";
    }
}
