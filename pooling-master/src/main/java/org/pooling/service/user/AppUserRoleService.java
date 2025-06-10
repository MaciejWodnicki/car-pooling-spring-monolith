package org.pooling.service.user;

import org.pooling.domain.user.AppUserRole;

import java.util.List;

public interface AppUserRoleService {
    List<AppUserRole> listAppUserRoles();
    AppUserRole getAppUserRole(long id);

}
