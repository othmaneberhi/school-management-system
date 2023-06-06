package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Teacher;
import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.excpetion.NotFoundException;
import com.ensah.schoolmanagementsystem.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users/{id}")
//    public String getUser(@PathVariable("id") Long id){
//        Optional<User> user = userService.getUserById(id);
//        if(user.isEmpty()){
//            throw new NotFoundException("User not found");
//        }
//        if(user.get().getAccount()==null){
//
//        }
//        String roleName = user.get().getAccount().getRole().getRoleName();
//        if(roleName.equals("ROLE_TEACHER")){
//            return "redirect:/teachers/"+user.get().getId();
//        }
//        if(roleName.equals("ROLE_STUDENT")){
//            return "redirect:/students/"+user.get().getId();
//        }
//        if(roleName.equals("ROLE_SCHOOL_ADMINISTRATOR")){
//            return "redirect:/school-administrators/"+user.get().getId();
//        }
//        if(roleName.equals("ROLE_ADMIN")){
//            return "redirect:/admin/"+user.get().getId();
//        }
//        return "";
//    }
}
