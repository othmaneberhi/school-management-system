package com.ensah.schoolmanagementsystem.security;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof DisabledException) {
            response.sendRedirect("/login?error=disabled");
            return;
        }
        else if (exception instanceof LockedException) {
            response.sendRedirect("/login?error=locked");
            return;
        } else if (exception instanceof CredentialsExpiredException || exception instanceof AccountExpiredException) {
            response.sendRedirect("/login?error=expired");
            return;
        } else {
            response.sendRedirect("/login?error=other");
        }
    }
}
