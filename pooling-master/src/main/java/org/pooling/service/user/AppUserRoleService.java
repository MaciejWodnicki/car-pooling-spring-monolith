package org.pooling.service.user;

import org.pooling.domain.user.AppUserRole;

import java.util.List;

public interface AppUserRoleService {
    void addAppUserRole(AppUserRole appUserRole);
    List<AppUserRole> listAppUserRoles();
    AppUserRole getAppUserRole(long id);

}
