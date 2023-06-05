package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleJpaRep;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleJpaRep roleJpaRep;

    public RoleServiceImp(RoleJpaRep roleJpaRep) {
        this.roleJpaRep = roleJpaRep;
    }

    @Override
    public Role findRoleById(Long id) {
        return roleJpaRep.findById(id).get();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleJpaRep.findAll();
    }

    @Override
    public List<Role> getRoles() {
        return roleJpaRep.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleJpaRep.save(role);
    }
}
