package org.pooling.service.user;

import jakarta.transaction.Transactional;
import org.pooling.domain.user.Address;
import org.pooling.repository.user.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Transactional
    public void editAddress(Address address) {
        addressRepository.save(address);
    }

    @Transactional
    public List<Address> listAddresses() {
        return addressRepository.findAll();
    }

    @Transactional
    public void removeAddress(long id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    public Address getAddress(long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
