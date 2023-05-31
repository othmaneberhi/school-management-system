package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Role;
import com.ensah.schoolmanagementsystem.repository.IRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
