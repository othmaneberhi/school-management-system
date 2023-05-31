package com.ensah.schoolmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping("/not-found")
    public String showNotFound(){
        return "pages/error/error-404";
    }

//    @GetMapping("/access-denied")
//    public String showAccessDenied() {
//        return "pages/error/access-denied";
//    }

}
