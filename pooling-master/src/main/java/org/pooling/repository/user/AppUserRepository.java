package org.pooling.repository.user;

import jakarta.transaction.Transactional;
import org.pooling.domain.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findByLastName(String LastName);
    AppUser findById(long id);

    AppUser findByLogin(String login);
    List<AppUser> findAllByEnabledIsFalse();
}
