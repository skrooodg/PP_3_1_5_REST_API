package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/user")
    public String showUsers(Model model, Principal principal) {
        User princ = userServiceImp.findByName(principal.getName());
        model.addAttribute("princ", princ);
        return "user";
    }
//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userServiceImp.findOne(id));
//        model.addAttribute("titleTable", "Страница пользователя:");
//        return "user";
//    }
}
////    @GetMapping("/addUser")
////    public String addNewUser(Model model, @ModelAttribute("user") User user) {
////        List<Role> roles = roleServiceImp.getRoles();
////        model.addAttribute("rolesAdd", roles);
////        return "creation";
////    }
//
////    @PostMapping("/user-creation")
////    public String addCreateNewUser(User user) {
////        try {
////            userServiceImp.save(user);
////        } catch (Exception er) {
////            System.err.println("Пользователь с таким email уже существует!");
////        }
////        return "redirect:/admin";
////    }
//
////    @PatchMapping("/user-update")
////    public String updateUser(Long id) {
////        try {
////            userServiceImp.update(id);
////        } catch (Exception e) {
////            System.err.println("Пользователь с таким логином уже существует!");
////        }
////        return "redirect:/admin";
////    }
//
////    @GetMapping("/user-update/{id}")
////    public String updateUserForm(@PathVariable("id") Long id, Model model) {
////        model.addAttribute("user", userServiceImp.getUser(id));
////        return "admin";
////    }
////
////    @GetMapping("/delete/{id}")
////    public String deleteUserForm(@PathVariable("id") Long id, Model model) {
////        model.addAttribute("user", userServiceImp.getUser(id));
////        return "admin";
////    }
////
////    @DeleteMapping("/user-delete")
////    public String deleteUser(Long id) {
////        userServiceImp.delete(id);
////        return "redirect:/admin";
////    }
//}
