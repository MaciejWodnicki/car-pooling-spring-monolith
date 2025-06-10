package org.pooling.service.user;

import org.pooling.domain.user.AppUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;

public interface AppUserService {

    public void addAppUser(AppUser appUser);

    @PreAuthorize("hasRole('ROLE_ADMIN') OR (#appUser.login == principal.username)")
    public void editAppUser(AppUser appUser);
    public List<AppUser> listAppUser();

    @Secured("ROLE_ADMIN")
    public void removeAppUser(long id);
    public AppUser getAppUser(long id);

    AppUser findByLogin(String login);

    void activateInactiveAppUsers();

    Set<AppUser> getUsersByRole(String manager);
}
