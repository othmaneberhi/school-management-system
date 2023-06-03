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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
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
                                @RequestParam("roleName") String roleName,
                                RedirectAttributes redirectAttributes){
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
        return "redirect:/admin/students/"+user_id;
    }

//    @PostMapping("/account/{id}/enabled")
//    public String setEnabled(@PathVariable("id")Long id,
//                             @RequestParam("enable") boolean enable){
//        Optional<Account> account = accountService.getAccountById(id);
//        if(account.isEmpty()){
//            throw new NotFoundException("account not found");
//        }
//        account.get().setEnabled(enable);
//        accountService.updateAccount(account.get());
//        return "redirect:/admin/students/"+account.get().getUser().getId();
//    }

//    @GetMapping("/accounts/{id}/edit")
//    public String accountEditPage(Model model,
//                                  @PathVariable("id") Long id){
//        Optional<Account> account = accountService.getAccountById(id);
//        if(account.isEmpty()){
//            throw new NotFoundException("Account not found");
//        }
//        model.addAttribute("account", account);
//
//        return "accounts/edit-account";
//    }

    @PostMapping("/accounts/{id}/edit")
    public String accountEdit(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("account") Account newAccount,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request){
        String sourceUrl = request.getHeader("Referer");
        if(result.hasErrors()){
            return "redirect:" + sourceUrl;
        }
        Optional<Account> account = accountService.getAccountById(id);
        if(account.isEmpty()){
            throw new NotFoundException("Account not found");
        }

        account.get().setEnabled(newAccount.isEnabled());
        account.get().setRole(newAccount.getRole());
        account.get().setLocked(newAccount.isLocked());
        accountService.updateAccount(account.get());

        redirectAttributes.addFlashAttribute("accountUpdatedMessage","Account updated successfully");
        return "redirect:" + sourceUrl;
    }

//    @GetMapping("/accounts")
//    public String accounts(Model model){
//       List<Account> accountList = accountService.getAllAccounts();
//       model.addAttribute("accounts",accountList);
//        return "accounts/accounts";
//    }
//    @GetMapping("/accounts/{id}")
//    public String account(@PathVariable("id")Long id,
//                          Model model){
//        Optional<Account> account = accountService.getAccountById(id);
//        if(account.isEmpty()){
//            throw new NotFoundException("Account not found");
//        }
//        model.addAttribute("account",account);
//        return "accounts/account";
//    }
}
