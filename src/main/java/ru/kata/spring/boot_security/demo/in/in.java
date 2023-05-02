package ru.kata.spring.boot_security.demo.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleJpaRep;
import ru.kata.spring.boot_security.demo.repositories.UserJpaRep;


import java.util.HashSet;
import java.util.Set;

@Component
public class in implements CommandLineRunner {
    private final UserJpaRep userJpaRep;
    private final RoleJpaRep roleJpaRep;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public in (UserJpaRep userJpaRep, RoleJpaRep roleJpaRep, PasswordEncoder passwordEncoder) {
        this.userJpaRep = userJpaRep;
        this.roleJpaRep = roleJpaRep;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... arg) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleJpaRep.save(roleAdmin);
        roleJpaRep.save(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);


        // пользователи Admin  и User
        User userAdmin = new User(1l,"Kirill", "Yuni", 10, "aasf","admin", passwordEncoder.encode("admin"), adminRoles);
        User userUser = new User(2l, "Regina", "Uni", 25, "TAT", "user", passwordEncoder.encode("user"), userRoles);
        System.out.println(userAdmin);
        userJpaRep.save(userAdmin);
        System.out.println(userUser);
        userJpaRep.save(userUser);
    }
}