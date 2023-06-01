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
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User not found with email: "+email);
        }
        Account account = user.getAccount();
        return new AccountPrincipal(account);
    }
}
