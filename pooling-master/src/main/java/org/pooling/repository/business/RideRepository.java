package org.pooling.repository.business;

import jakarta.transaction.Transactional;
import org.pooling.domain.business.Ride;
import org.pooling.domain.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    public List<Ride> findAll();

}