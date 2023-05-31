package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        this.userRepository.save(user);
    }
}
