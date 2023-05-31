package com.ensah.schoolmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/student")
    public String studentHome(){
        return "pages/student/home";
    }
    @GetMapping("/administrator")
    public String administratorHome(){
        return "pages/administrator/home";
    }
    @GetMapping("/teacher")
    public String teacherHome(){
        return "pages/teacher/home";
    }
    @GetMapping("/admin")
    public String adminHome(){
        return "pages/admin/home";
    }

}
