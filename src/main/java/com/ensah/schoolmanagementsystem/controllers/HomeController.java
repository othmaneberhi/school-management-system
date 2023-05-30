package com.ensah.schoolmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "pages/page1";
    }

    @RequestMapping("/login")
    public String login(){
        return "pages/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "pages/register";
    }
}
