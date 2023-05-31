package com.ensah.schoolmanagementsystem.repository;

import com.ensah.schoolmanagementsystem.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
}
