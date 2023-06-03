package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.Student;
import com.ensah.schoolmanagementsystem.excpetion.NotFoundException;
import com.ensah.schoolmanagementsystem.service.IAccountService;
import com.ensah.schoolmanagementsystem.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
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

    private Boolean emailUsed(Student newStudent){
        Optional<Student> student = studentService.getStudentByEmail(newStudent.getEmail());

        return student.isPresent() && !(student.get().getId().equals(newStudent.getId()));
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

    @GetMapping("students/{id}/edit")
    public String studentEditPage(@PathVariable("id") Long id,
                                  Model model){
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isEmpty()){
            throw new NotFoundException("Student not found");
        }
        model.addAttribute("student",student.get());
        return "pages/students/edit-student";
    }
    @PostMapping("/students/{id}/edit")
    public String studentEdit(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("student") Student newStudent,
                              BindingResult result,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes){
        String sourceUrl = request.getHeader("Referer");

        if (emailUsed(newStudent)) {
            result.rejectValue("email", "invalid.email", "Email already used");
        }

        if(result.hasErrors()){
            return "pages/students/edit-student";
        }
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isEmpty()){
            throw new NotFoundException("Student not found");
        }

        student.get().setFirstName(newStudent.getFirstName());
        student.get().setLastName(newStudent.getLastName());
        student.get().setArabicFirstName(newStudent.getArabicFirstName());
        student.get().setArabicLastName(newStudent.getArabicLastName());
        student.get().setEmail(newStudent.getEmail());
        student.get().setPhone(newStudent.getPhone());
        student.get().setArabicFirstName(newStudent.getArabicFirstName());
        student.get().setArabicLastName(newStudent.getArabicLastName());
        student.get().setPicture(newStudent.getPicture());
        student.get().setCne(newStudent.getCne());
        student.get().setCin(newStudent.getCin());
        student.get().setBirthDate(newStudent.getBirthDate());
        studentService.updateStudent(student.get());


        redirectAttributes.addFlashAttribute("studentUpdatedMessage","Student's informations updated successfully");
        return "redirect:" + sourceUrl;
    }

    @GetMapping("students/add")
    public String studentAddPage(Model model){
        model.addAttribute("student",new Student());
        return "pages/students/add-student";
    }

    @PostMapping("/students/add")
    public String studentEdit(@Valid @ModelAttribute("student") Student newStudent,
                              BindingResult result,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes){
        String sourceUrl = request.getHeader("Referer");

        if (emailUsed(newStudent)) {
            result.rejectValue("email", "invalid.email", "Email already used");
        }

        if(result.hasErrors()){
            return "pages/students/add-student";
        }

        studentService.addStudent(newStudent);
        redirectAttributes.addFlashAttribute("studentCreatedMessage","Student created successfully");
        return "redirect:/admin/students/"+newStudent.getId();
    }
}
