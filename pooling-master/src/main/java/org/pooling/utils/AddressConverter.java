package org.pooling.utils;

import org.pooling.domain.user.Address;
import org.pooling.service.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class AddressConverter implements Converter<String, Address> {

    private AddressService addressService;

    @Autowired
    public AddressConverter(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public Address convert(String source) {
        return addressService.getAddress(Integer.parseInt(source));
    }

}
