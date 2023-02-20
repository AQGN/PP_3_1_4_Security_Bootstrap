package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getRolesSet() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
