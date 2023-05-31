package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

}
