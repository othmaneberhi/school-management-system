package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Student;
import com.ensah.schoolmanagementsystem.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> studentList = studentService.getAllStudentsByOrderByLastName();
        model.addAttribute("students",studentList);
        return "pages/admin/students";
    }
}
