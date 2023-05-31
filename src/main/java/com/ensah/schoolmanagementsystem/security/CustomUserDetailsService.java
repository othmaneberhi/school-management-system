package com.ensah.schoolmanagementsystem.security;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.bo.User;
import com.ensah.schoolmanagementsystem.repository.IAccountRepository;
import com.ensah.schoolmanagementsystem.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("looking for user with email: "+email);
        User user = userRepository.findByEmail(email);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found with email: "+email);
        }
        Account account = user.getAccount();

        System.out.println("user found= "+user);
        System.out.println("account found= "+account);
        System.out.println("return account principal= "+ new AccountPrincipal(account));

        return new AccountPrincipal(account);
    }
}
