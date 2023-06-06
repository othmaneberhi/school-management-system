package com.ensah.schoolmanagementsystem.repository;

import com.ensah.schoolmanagementsystem.bo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account,Long> {
    public Account findByUserId(Long userId);
    public List<Account> findAllByOrderById();
    @Query("SELECT acc FROM Account acc JOIN FETCH acc.user ORDER BY acc.id")
    List<Account> findAllWithUserOrderedById();
}
