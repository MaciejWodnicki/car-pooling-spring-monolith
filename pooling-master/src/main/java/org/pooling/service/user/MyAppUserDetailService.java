package org.pooling.service.user;

import org.pooling.domain.user.AppUser;
import org.pooling.domain.user.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myAppUserDetailsService")
public class MyAppUserDetailService implements UserDetailsService {

    private AppUserService appUserService;

    @Autowired
    public MyAppUserDetailService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException{
        AppUser appUser = appUserService.findByLogin(login);
        List<GrantedAuthority> authorities = buildUserAuthority(appUser.getAppUserRole());
        return buildUserForAuthentication(appUser, authorities);
    }

    private User buildUserForAuthentication(AppUser appUser, List<GrantedAuthority> authorities) {
        return new User(appUser.getLogin(), appUser.getPassword(), appUser.isEnabled(),
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<AppUserRole> appUserRole) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (AppUserRole appUserRoleItem : appUserRole) {
            setAuths.add(new SimpleGrantedAuthority(appUserRoleItem.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }
}
