package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUsers(Model model, Principal principal) {
        User princ = userService.findByName(principal.getName());
        model.addAttribute("princ", princ);
        return "user";
    }

//    @GetMapping("/user")
//    public ResponseEntity<User> showUser(Principal principal) {
//        User user = userService.findByName(principal.getName());
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}