package com.ensah.schoolmanagementsystem.excpetion;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Model model,NotFoundException exception){
        model.addAttribute("message",exception.getMessage());
        return "pages/error/error-404";
    }
}
