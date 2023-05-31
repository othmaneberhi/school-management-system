package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Role;

public interface IRoleService {
    public void addRole(Role role);
    public Role getRoleByRoleName(String roleName);
}
