package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.User;

import java.util.Optional;

public interface IUserService {
    public void addUser(User user);
    public Optional<User> getUserById(Long id);
    public void updateUser(User user);
}
