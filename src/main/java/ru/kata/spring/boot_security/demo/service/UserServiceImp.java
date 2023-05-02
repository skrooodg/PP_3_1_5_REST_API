package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserJpaRep;

import java.util.List;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
    private final UserJpaRep userJpaRep;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserJpaRep userJpaRep, @Lazy PasswordEncoder passwordEncoder) {
        this.userJpaRep = userJpaRep;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRep.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getUserName(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public List<User> findAll() {
        return userJpaRep.findAll();
    }
    @Override
    public User findOne(Long id) {
        return userJpaRep.findById(id).orElseThrow();
    }
    public User findByName(String username) {
        return userJpaRep.findByUserName(username);
    }

    @Override
    public User getUser(Long id) {
        return userJpaRep.findById(id).get();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaRep.save(user);
    }
    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaRep.save(user);
    }
    @Override
    public void delete(Long id) {
        userJpaRep.deleteById(id);
    }
}
