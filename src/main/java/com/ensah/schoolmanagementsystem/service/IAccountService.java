package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Account;

import java.util.List;
import java.util.Optional;


public interface IAccountService {
    public void addAccount(Account account);
    public Optional<Account> getAccountById(Long id);
    public void updateAccount(Account account);

    public List<Account> getAllAccounts();
    public void deleteAccount(Account account);
    public List<Account> getAllAccountsByOrderById();
    public List<Account> getAllAccountsWithUserByOrderById();
}
