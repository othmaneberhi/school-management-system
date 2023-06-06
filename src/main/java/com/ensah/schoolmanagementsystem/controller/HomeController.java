package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.Student;
import com.ensah.schoolmanagementsystem.bo.Teacher;
import com.ensah.schoolmanagementsystem.service.IAccountService;
import com.ensah.schoolmanagementsystem.service.IStudentService;
import com.ensah.schoolmanagementsystem.service.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    final IAccountService accountService;
    final IStudentService studentService;
    final ITeacherService teacherService;

    public HomeController(IAccountService accountService, IStudentService studentService, ITeacherService teacherService) {
        this.accountService = accountService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

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
    public String adminHome(Model model){
        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllSTeachers();
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("studentsCount",students.size());
        model.addAttribute("teachersCount",teachers.size());
        model.addAttribute("accountsCount",accounts.size());
        return "pages/admin/home";
    }

}
