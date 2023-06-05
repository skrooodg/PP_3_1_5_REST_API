package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("allUsers", userServiceImp.findAll());
        User princ = userServiceImp.findByName(principal.getName());
        model.addAttribute("princ", princ);
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleServiceImp.getRoles());
        model.addAttribute("titleTable", "Список всех пользователей:");
        return "admin";
    }

    @GetMapping("/currentUser")
    public String showUsers(Model model, Principal principal) {
        User princ = userServiceImp.findByName(principal.getName());
        model.addAttribute("princ", princ);
        return "user";
    }
}
