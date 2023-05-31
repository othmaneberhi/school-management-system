package com.ensah.schoolmanagementsystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_STUDENT")) {
            redirectStrategy.sendRedirect(request, response, "/student");
        } else if (roles.contains("ROLE_TEACHER")) {
            redirectStrategy.sendRedirect(request, response, "/teacher");
        } else if (roles.contains("ROLE_SCHOOL_ADMINISTRATOR")) {
            redirectStrategy.sendRedirect(request, response, "/school-administrator");
        } else if (roles.contains("ROLE_ADMIN")) {
            redirectStrategy.sendRedirect(request, response, "/admin");
        } else {
            throw new IllegalStateException();
        }
    }
}
