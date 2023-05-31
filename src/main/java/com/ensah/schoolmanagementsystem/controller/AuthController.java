package com.ensah.schoolmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(){
        return "pages/auth/login";
    }
    @GetMapping("/register")
    public String registerForm(){
        return "pages/auth/register";
    }
}
