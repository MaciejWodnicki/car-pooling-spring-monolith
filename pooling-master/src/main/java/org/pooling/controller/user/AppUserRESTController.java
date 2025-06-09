package org.pooling.controller.user;

import org.pooling.domain.user.AppUser;
import org.pooling.repository.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appUserRest")
public class AppUserRESTController {
    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserRESTController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "application/json")
    public AppUser getAppUserInJSON(@PathVariable("login") String login) {
        return appUserRepository.findByLogin(login);
    }

    @RequestMapping(value = "/{login}.xml", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserInXML(@PathVariable("login") String login) {
        return appUserRepository.findByLogin(login);
    }
}
