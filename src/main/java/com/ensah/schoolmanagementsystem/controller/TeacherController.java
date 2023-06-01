package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.service.ITeacherService;
import org.springframework.stereotype.Controller;

@Controller
public class TeacherController {
    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
