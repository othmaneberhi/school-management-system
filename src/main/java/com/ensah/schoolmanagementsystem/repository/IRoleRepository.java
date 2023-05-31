package com.ensah.schoolmanagementsystem.repository;

import com.ensah.schoolmanagementsystem.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String roleName);
}
