package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.Student;
import com.ensah.schoolmanagementsystem.excpetion.NotFoundException;
import com.ensah.schoolmanagementsystem.service.IAccountService;
import com.ensah.schoolmanagementsystem.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class StudentController {
    private final IStudentService studentService;
    private final IAccountService accountService;

    public StudentController(IStudentService studentService, IAccountService accountService) {
        this.studentService = studentService;
        this.accountService = accountService;
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> studentList = studentService.getAllStudentsByOrderByLastName();
        model.addAttribute("students",studentList);
        return "pages/admin/students";
    }
    @GetMapping("/students/{id}")
    public String showStudentById(@PathVariable("id") Long id, Model model){
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isEmpty()){
            throw new NotFoundException("Student not found");
        }
        model.addAttribute("student",student.get());

        model.addAttribute("account", new Account());
        return "pages/admin/student";
    }
}
