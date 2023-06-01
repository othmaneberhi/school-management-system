package com.ensah.schoolmanagementsystem;

import com.ensah.schoolmanagementsystem.bo.*;
import com.ensah.schoolmanagementsystem.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class SchoolManagementSystemApplication implements CommandLineRunner {

	private final IAccountService accountService;
	private final IUserService userService;
	private final IRoleService roleService;
	private final IStudentService studentService;
	private final ITeacherService teacherService;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

	public SchoolManagementSystemApplication(IAccountService accountService, IUserService userService, IRoleService roleService, IStudentService studentService, ITeacherService teacherService) {
		this.accountService = accountService;
		this.userService = userService;
		this.roleService = roleService;
		this.studentService = studentService;
		this.teacherService = teacherService;
	}


	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("app running ...");

		////ROLES
		// this.createRoles();

		////USERS (not specified)
		//this.generateRandomUsers();

		////ADMIN (user + account)
		//this.createAdmin();

		////STUDENTS (users)
		//this.generateRandomStudents();

		////TEACHERS (users)
//		this.generateRandomTeachers();

	}

	private void createAdmin() {
		User user = new User();
		user.setArabicFirstName("عثمان");
		user.setArabicLastName("برهي");
		user.setCreatedAt(new Date());
		user.setDeletedAt(null);
		user.setEmail("admin@gmail.com");
		user.setFirstName("Othmane");
		user.setLastName("Berhi");
		user.setPhone("0602596700");
		user.setPicture("profile.jpg");
		user.setCin("UC137167");
		Role role = roleService.getRoleByRoleName("ROLE_ADMIN");
		Account account = new Account();
		account.setCreatedAt(new Date());
		account.setDeletedAt(null);
		account.setEnabled(true);
		account.setExpired(false);
		account.setPassword(passwordEncoder.encode("1234"));
		account.setUser(user);
		account.setRole(role);
		account.setCredentialsExpired(false);
		account.setLocked(false);
		user.setAccount(account);
		account.setUser(user);
		userService.addUser(user);
		accountService.addAccount(account);
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

	public void generateRandomStudents() throws ParseException {
		String[] arabicFirstNames = {"علي", "محمد", "أحمد", "محمود", "يوسف", "عبدالله", "مصطفى", "رضوان", "زينب", "فاطمة"};
		String[] arabicLastNames = {"أحمد", "محمود", "علي", "حسين", "رضوان", "زينب", "عبدالله", "عمر", "فاطمة", "يوسف"};
		String[] firstNames = {"Ali", "Mohamed", "Ahmed", "Mahmoud", "Youssef", "Abdullah", "Mustafa", "Redouane", "Zainab", "Fatima"};
		String[] lastNames = {"Ahmed", "Mahmoud", "Ali", "Hussein", "Redouane", "Zainab", "Abdullah", "Omar", "Fatima", "Youssef"};
		String[] domains = {"example.com", "test.com", "domain.com", "company.com", "website.com"};

		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			Student student = new Student();
			student.setArabicFirstName(arabicFirstNames[random.nextInt(arabicFirstNames.length)]);
			student.setArabicLastName(arabicLastNames[random.nextInt(arabicLastNames.length)]);
			student.setCreatedAt(new Date());
			student.setDeletedAt(null);
			student.setEmail(generateRandomEmail(firstNames[random.nextInt(firstNames.length)], lastNames[random.nextInt(lastNames.length)], domains[random.nextInt(domains.length)],i));
			student.setFirstName(firstNames[random.nextInt(firstNames.length)]);
			student.setLastName(lastNames[random.nextInt(lastNames.length)]);
			student.setPhone(generateRandomPhone());
			student.setPicture("profile.jpg");
			student.setCin("UC" + (random.nextInt(900000) + 100000));
			student.setCne("M" + (random.nextInt(900000000) + 100000000));

			// Generate a random birth date between 1990-01-01 and 2005-12-31
			String startDateString = "1990-01-01";
			String endDateString = "2005-12-31";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = dateFormat.parse(startDateString);
			Date endDate = dateFormat.parse(endDateString);
			long startMillis = startDate.getTime();
			long endMillis = endDate.getTime();
			long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
			Date randomBirthDate = new Date(randomMillisSinceEpoch);
			student.setBirthDate(randomBirthDate);
			student.setInscription(null);

			studentService.addStudent(student);
		}
	}

	public void generateRandomUsers() {
		String[] arabicFirstNames = {"علي", "محمد", "أحمد", "محمود", "يوسف", "عبدالله", "مصطفى", "رضوان", "زينب", "فاطمة"};
		String[] arabicLastNames = {"أحمد", "محمود", "علي", "حسين", "رضوان", "زينب", "عبدالله", "عمر", "فاطمة", "يوسف"};
		String[] firstNames = {"Ali", "Mohamed", "Ahmed", "Mahmoud", "Youssef", "Abdullah", "Mustafa", "Redouane", "Zainab", "Fatima"};
		String[] lastNames = {"Ahmed", "Mahmoud", "Ali", "Hussein", "Redouane", "Zainab", "Abdullah", "Omar", "Fatima", "Youssef"};
		String[] domains = {"example.com", "gmail.com", "domain.com", "company.com", "website.com"};

		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setArabicFirstName(arabicFirstNames[random.nextInt(arabicFirstNames.length)]);
			user.setArabicLastName(arabicLastNames[random.nextInt(arabicLastNames.length)]);
			user.setCin("UC" +(random.nextInt(900000) + 100000));
			user.setCreatedAt(new Date());
			user.setDeletedAt(null);
			user.setEmail(generateRandomEmail(firstNames[random.nextInt(firstNames.length)], lastNames[random.nextInt(lastNames.length)], domains[random.nextInt(domains.length)],i));
			user.setFirstName(firstNames[random.nextInt(firstNames.length)]);
			user.setLastName(lastNames[random.nextInt(lastNames.length)]);
			user.setPhone(generateRandomPhone());
			user.setPicture("profile.jpg");

			userService.addUser(user);
		}
	}

	public void generateRandomTeachers() throws ParseException {
		String[] arabicFirstNames = {"علي", "محمد", "أحمد", "محمود", "يوسف", "عبدالله", "مصطفى", "رضوان", "زينب", "فاطمة"};
		String[] arabicLastNames = {"أحمد", "محمود", "علي", "حسين", "رضوان", "زينب", "عبدالله", "عمر", "فاطمة", "يوسف"};
		String[] firstNames = {"Ali", "Mohamed", "Ahmed", "Mahmoud", "Youssef", "Abdullah", "Mustafa", "Redouane", "Zainab", "Fatima"};
		String[] lastNames = {"Ahmed", "Mahmoud", "Ali", "Hussein", "Redouane", "Zainab", "Abdullah", "Omar", "Fatima", "Youssef"};
		String[] domains = {"example.com", "test.com", "domain.com", "company.com", "website.com"};
		String[] specializations = {"Mathematics", "Physics", "Chemistry", "Biology", "Computer Science", "English", "History", "Geography"};

		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			Teacher teacher = new Teacher();
			teacher.setArabicFirstName(arabicFirstNames[random.nextInt(arabicFirstNames.length)]);
			teacher.setArabicLastName(arabicLastNames[random.nextInt(arabicLastNames.length)]);
			teacher.setCin("UC" +(random.nextInt(900000) + 100000));
			teacher.setCreatedAt(new Date());
			teacher.setDeletedAt(null);
			teacher.setEmail(generateRandomEmail(firstNames[random.nextInt(firstNames.length)], lastNames[random.nextInt(lastNames.length)], domains[random.nextInt(domains.length)],i));
			teacher.setFirstName(firstNames[random.nextInt(firstNames.length)]);
			teacher.setLastName(lastNames[random.nextInt(lastNames.length)]);
			teacher.setPhone(generateRandomPhone());
			teacher.setPicture("profile.jpg");
			teacher.setSpecialization(specializations[random.nextInt(specializations.length)]);
			teacher.setAbsences(null);
			teacher.setCoordinations(null);
			teacherService.addTeacher(teacher);
		}
	}

	private String generateRandomEmail(String firstName, String lastName, String domain,int i) {
		return firstName.toLowerCase() + "." + lastName.toLowerCase()+i+ "@" + domain;
	}

	private String generateRandomPhone() {
		StringBuilder phone = new StringBuilder("06");
		Random random = new Random();

		for (int i = 0; i < 8; i++) {
			phone.append(random.nextInt(10));
		}

		return phone.toString();
	}
}
