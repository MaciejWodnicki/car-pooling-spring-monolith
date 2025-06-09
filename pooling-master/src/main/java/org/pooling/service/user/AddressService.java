package org.pooling.service.user;

import org.pooling.domain.user.Address;

import java.util.List;

public interface AddressService {
    public void addAddress(Address address);
    public void editAddress(Address address);
    public List<Address> listAddresses();
    public void removeAddress(long id);
    public Address getAddress(long id);

}
