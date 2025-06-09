package org.pooling.repository.user;

import jakarta.transaction.Transactional;
import org.pooling.domain.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByStreet(String street);
    Address findAddressById(long id);
}
