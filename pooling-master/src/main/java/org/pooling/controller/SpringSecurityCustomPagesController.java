package org.pooling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringSecurityCustomPagesController {

    @RequestMapping(value = "/login")
    public String customLogin(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              Model model) {

        System.out.println("custom Login Called !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (error != null) {
            model.addAttribute("error", "Invalid Username and password");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully");
        }
        return "login";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
