package com.ensah.schoolmanagementsystem.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null && Integer.parseInt(status.toString()) == HttpStatus.NOT_FOUND.value()) {
            return "pages/error/error-404";
        }

        // Return an error page for other errors
        return "pages/error/other-errors";
    }

    @RequestMapping("/access-denied")
    public String showAccessDenied(){
        return "pages/error/access-denied";
    }

}
