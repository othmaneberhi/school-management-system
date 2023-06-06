package com.ensah.schoolmanagementsystem.controller;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.Role;
import com.ensah.schoolmanagementsystem.bo.Student;
import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.excpetion.NotFoundException;
import com.ensah.schoolmanagementsystem.service.IAccountService;
import com.ensah.schoolmanagementsystem.service.IRoleService;
import com.ensah.schoolmanagementsystem.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.*;

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

    public boolean isRoleNameValid(String roleName) {
        return roleName != null && (roleName.equals("ROLE_STUDENT") || roleName.equals("ROLE_TEACHER") || roleName.equals("ROLE_SCHOOL_ADMINISTRATOR"));
    }


    @PostMapping("/create-account/{id}")
    public String createAccount(HttpServletRequest request,
                                @PathVariable("id") Long user_id,
                                @RequestParam("roleName") String roleName,
                                RedirectAttributes redirectAttributes){
        String sourceUrl = request.getHeader("Referer");
        Optional<User> user = userService.getUserById(user_id);
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        Role role = roleService.getRoleByRoleName(roleName);
        if(role==null){
            throw new NotFoundException("Role "+roleName+" not found");
        }
        if(user.get().getAccount()!=null){
            return "redirect:/admin/students/"+user_id;
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
        return "redirect:" + sourceUrl;
    }

    @PostMapping("/accounts/{id}/toggleEnabled")
    public String setEnabled(@PathVariable("id")Long id,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request){
        String sourceUrl = request.getHeader("Referer");
        Optional<Account> account = accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("account not found");
        }
        account.get().setEnabled(!account.get().isEnabled());
        accountService.updateAccount(account.get());
        String op = account.get().isEnabled()? "enabled":"disabled";
        redirectAttributes.addFlashAttribute("accountUpdatedMessage","Account "+ op +" successfully");
        return "redirect:" + sourceUrl;
    }

    @PostMapping("/accounts/{id}/setRole")
    public String setRole(@PathVariable("id")Long id,@RequestParam(value = "role",required = true) String roleName, RedirectAttributes redirectAttributes,
                             HttpServletRequest request){
        String sourceUrl = request.getHeader("Referer");
        if (!isRoleNameValid(roleName)) {
            throw new NotFoundException("Role not found");
        }
        Optional<Account> account = accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("account not found");
        }
        Optional<Role> role = Optional.ofNullable(roleService.getRoleByRoleName(roleName));
        if(role.isEmpty()){
            throw new NotFoundException("Role not found");
        }

        account.get().setRole(role.get());
        accountService.updateAccount(account.get());
        redirectAttributes.addFlashAttribute("accountUpdatedMessage","Account's role updated successfully");
        return "redirect:" + sourceUrl;
    }

    @PostMapping("/accounts/{id}/reset-password")
    public String resetPassword(@PathVariable("id")Long id,
                                RedirectAttributes redirectAttributes,
                                HttpServletRequest request){
        String sourceUrl = request.getHeader("Referer");
        Optional<Account> account = accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("account not found");
        }
        String password = generateRandomPassword(10);
        account.get().setPassword(passwordEncoder.encode(password));
        accountService.addAccount(account.get());
        Map<String, Object> passwordReset= new HashMap<>();
        passwordReset.put("message", "password has been reset successfully");
        passwordReset.put("password", password);
        redirectAttributes.addFlashAttribute("passwordReset", passwordReset);
        return "redirect:" + sourceUrl;
    }

    @GetMapping("/accounts")
    public String accounts(Model model){
       List<Account> accountList = accountService.getAllAccountsWithUserByOrderById();
       model.addAttribute("accounts",accountList);
        return "pages/accounts/accounts";
    }
    @GetMapping("/accounts/{id}")
    public String getAccount(@PathVariable("id") Long id){
        Optional<Account> account= accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("Account not found");
        }
        Optional<User> user = Optional.ofNullable(account.get().getUser());
        if(user.isEmpty()){
            throw new NotFoundException("User liked to the account not found");
        }

        String roleName = user.get().getAccount().getRole().getRoleName();
        if(roleName.equalsIgnoreCase("ROLE_ADMIN")){
            return "redirect:/admin/admins/"+user.get().getId();
        }
        if (roleName.equalsIgnoreCase("ROLE_SCHOOL_ADMINISTRATOR")) {
            return "redirect:/admin/school-administrators/"+user.get().getId();
        }
        if (roleName.equalsIgnoreCase("ROLE_TEACHER")) {
            return "redirect:/admin/teachers/"+user.get().getId();
        }
        if (roleName.equalsIgnoreCase("ROLE_STUDENT")){
            return "redirect:/admin/students/"+user.get().getId();
        }
        return "redirect:/admin/accounts";
    }
    @GetMapping("/accounts/{id}/edit")
    public String editAccount(@PathVariable("id") Long id){
        Optional<Account> account= accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("Account not found");
        }
        Optional<User> user = Optional.ofNullable(account.get().getUser());
        if(user.isEmpty()){
            throw new NotFoundException("User liked to the account not found");
        }

        String roleName = user.get().getAccount().getRole().getRoleName();
        if(roleName.equalsIgnoreCase("ROLE_ADMIN")){
            return "redirect:/admin/admins/"+user.get().getId()+"/edit";
        }
        if (roleName.equalsIgnoreCase("ROLE_SCHOOL_ADMINISTRATOR")) {
            return "redirect:/admin/school-administrators/"+user.get().getId()+"/edit";
        }
        if (roleName.equalsIgnoreCase("ROLE_TEACHER")) {
            return "redirect:/admin/teachers/"+user.get().getId()+"/edit";
        }
        if (roleName.equalsIgnoreCase("ROLE_STUDENT")){
            return "redirect:/admin/students/"+user.get().getId()+"/edit";
        }
        return "redirect:/admin/accounts";
    }

    @PostMapping("/accounts/{id}/delete")
    public String deleteAccount(@PathVariable("id")Long id){
        Optional<Account> account = accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("Account not found");
        }
        User student = account.get().getUser();
        student.setAccount(null);
        accountService.deleteAccount(account.get());
        return "redirect:/admin/accounts";
    }


}
