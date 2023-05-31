package com.ensah.schoolmanagementsystem;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.Role;
import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.service.IAccountService;
import com.ensah.schoolmanagementsystem.service.IRoleService;
import com.ensah.schoolmanagementsystem.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SchoolManagementSystemApplication implements CommandLineRunner {

	private final IAccountService accountService;
	private final IUserService userService;
	private final IRoleService roleService;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

	public SchoolManagementSystemApplication(IAccountService accountService, IUserService userService, IRoleService roleService) {
		this.accountService = accountService;
		this.userService = userService;
		this.roleService = roleService;
	}


	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("app running ...");

//		this.createRoles();
//
//
//
//		User user = new User();
//		user.setArabicFirstName("علي");
//		user.setArabicLastName("أحمد");
//		user.setCin("123456789");
//		user.setCreatedAt(new Date());
//		user.setDeletedAt(null);
//		user.setEmail("ali@example.com");
//		user.setFirstName("Ali");
//		user.setLastName("Ahmed");
//		user.setPhone("0602596700");
//		user.setPicture("profile.jpg");
//		user.setCin("UC137167");
//
//
//		Role role = roleService.getRoleByRoleName("ROLE_TEACHER");
//		Account account = new Account();
//		account.setCreatedAt(new Date());
//		account.setDeletedAt(null);
//		account.setEnabled(true);
//		account.setExpired(false);
//		account.setPassword(passwordEncoder.encode("1234"));
//		account.setUser(user);
//		account.setRole(role);
//		account.setCredentialsExpired(false);
//		account.setLocked(false);
//		user.setAccount(account);
//		account.setUser(user);
//
//
//		userService.addUser(user);
//		accountService.addAccount(account);


	}
	private void createRoles(){
		Role role1 = new Role();
		role1.setRoleName("ROLE_STUDENT");
		roleService.addRole(role1);
		Role role2 = new Role();
		role2.setRoleName("ROLE_SCHOOL_ADMINISTRATOR");
		roleService.addRole(role2);
		Role role3 = new Role();
		role3.setRoleName("ROLE_ADMIN");
		roleService.addRole(role3);
		Role role4 = new Role();
		role4.setRoleName("ROLE_TEACHER");
		roleService.addRole(role4);
	}
}
