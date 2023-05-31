package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Account;
import com.ensah.schoolmanagementsystem.repository.IAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) {
        this.accountRepository.save(account);
    }
}
