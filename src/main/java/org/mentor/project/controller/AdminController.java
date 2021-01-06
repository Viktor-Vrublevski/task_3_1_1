package org.mentor.project.controller;

import org.mentor.project.model.User;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/admin/users")
    public String tableWithUser(ModelMap model) {
        List<User> list = userService.getAll();
        model.addAttribute("users", list);
        return "table";
    }

    @GetMapping(value = "/admin/create")
    public String createUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping(value = "/admin/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/admin/update/{id}")
    public String getUpdate(@PathVariable(value = "id") long id, ModelMap model) {
        User user = userService.findById(id);
        System.out.println(user.getId());
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "/admin/update")
    public String update(@ModelAttribute("user") User user) {
        System.out.println(user.getId());

        userService.update(user);
        return "redirect:/admin/users";
    }
}

