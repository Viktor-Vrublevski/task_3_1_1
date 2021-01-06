package org.mentor.project.controller;

import org.mentor.project.model.Role;
import org.mentor.project.model.User;
import org.mentor.project.service.RoleService;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registry(ModelMap model) {
        if (roleService.getAllRoles().size() == 0) {
            roleService.create();
        }
        User user = new User();
        model.addAttribute("user", user);
        return "registry";
    }

    @PostMapping(value = "/registration")
    public String createUser(@ModelAttribute("user") User user) {
        List<Role> roles = new ArrayList<>();
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRole().equals("user")) {
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
        }
        if (user.getRole().equals("admin")) {
            roles.add(roleService.getRole(1));
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
        }

        userService.save(user);
        return "redirect:/login";
    }
}

