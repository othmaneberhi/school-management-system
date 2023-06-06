package com.ensah.schoolmanagementsystem.security;


import com.ensah.schoolmanagementsystem.bo.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AccountPrincipal implements UserDetails {
    private Account account;
    public AccountPrincipal(Account account) {
        this.account = account;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> arrayAuths = new ArrayList<GrantedAuthority>();
        if(account.getRole()!=null){
            SimpleGrantedAuthority auth = new SimpleGrantedAuthority(account.getRole().getRoleName());
            arrayAuths.add(auth);
        }
        return arrayAuths;
    }
    public String getFirstName() {
        return account.getUser().getFirstName();
    }
    public String getLastName() {
        return account.getUser().getLastName();
    }
    public String getEmail() {
        return account.getUser().getEmail();
    }
    @Override
    public String getPassword() {
        return account.getPassword();
    }
    @Override
    public String getUsername() {
        return account.getUser().getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return !account.isExpired();
    }
    @Override
    public boolean isAccountNonLocked() {
        return !account.isLocked();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return !account.isCredentialsExpired();
    }
    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }
}