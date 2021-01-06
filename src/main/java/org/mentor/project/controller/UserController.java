package org.mentor.project.controller;

import org.mentor.project.model.User;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/user")
    public String pageUser(ModelMap model) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = service.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }
}
