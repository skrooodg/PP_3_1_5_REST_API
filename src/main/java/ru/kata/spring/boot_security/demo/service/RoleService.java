package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public Role findRoleById(Long id);

    public List<Role> getAllRoles();
    List<Role> getRoles();
    public void addRole(Role role);
}
