package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;
    @Autowired
    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userServiceImp.findAll());
        model.addAttribute("titleTable1", "Список всех пользователей:");
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImp.findOne(id));
        model.addAttribute("titleTable", "Страница пользователя:");
        return "user";
    }

    @GetMapping("/addUser")
    public String addNewUser(Model model, @ModelAttribute("user") User user ) {
        List<Role> roles = roleServiceImp.getRoles();
        model.addAttribute("rolesAdd", roles);
        return "creation";
    }

    @PostMapping
    public String addCreateNewUser(@ModelAttribute("user") User user,Model model) {
        try {
            userServiceImp.save(user);
        } catch (Exception er) {
            System.err.println("Пользователь с таким логином уже существует!");
        }return "redirect:/admin";
    }

    @GetMapping("/{id}/editUser")
    public String updatePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userServiceImp.findOne(id));
        model.addAttribute("rolesAdd", roleServiceImp.getRoles());
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        try {
            userServiceImp.update(user);
        } catch (Exception e) {
        System.err.println("Пользователь с таким логином уже существует!");
    }
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImp.delete(id);
        return "redirect:/admin";
    }
}
