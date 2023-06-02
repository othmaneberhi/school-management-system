package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.Role;
import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.excpetion.NotFoundException;
import com.ensah.schoolmanagementsystem.service.IAccountService;
import com.ensah.schoolmanagementsystem.service.IRoleService;
import com.ensah.schoolmanagementsystem.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AccountController {
    private final IAccountService accountService;
    private final IUserService userService;
    private final IRoleService roleService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public AccountController(IAccountService accountService, IUserService userService, IRoleService roleService) {
        this.accountService = accountService;
        this.userService = userService;
        this.roleService = roleService;
    }

    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    @PostMapping("/create-account/{id}")
    public String createAccount(HttpServletRequest request,
                                @PathVariable("id") Long user_id,
                                RedirectAttributes redirectAttributes){
        Optional<User> user = userService.getUserById(user_id);
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        String roleName = request.getParameter("roleName");
        Role role = roleService.getRoleByRoleName(roleName);
        if(role==null){
            throw new NotFoundException("Role "+roleName+" not found");
        }
        Account account = new Account();
        account.setUser(user.get());
        account.setRole(role);
        String password = generateRandomPassword(10);
        account.setPassword(passwordEncoder.encode(password));
        accountService.addAccount(account);
        user.get().setAccount(account);
        userService.updateUser(user.get());


        Map<String, Object> accountCreated = new HashMap<>();
        accountCreated.put("message", "Account created successfully");
        accountCreated.put("password", password);

        redirectAttributes.addFlashAttribute("accountCreated",accountCreated);
        return "redirect:/admin/students/"+user_id;
    }
}
