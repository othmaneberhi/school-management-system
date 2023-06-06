package com.ensah.schoolmanagementsystem.repository;

import com.ensah.schoolmanagementsystem.bo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account,Long> {
    public Account findByUserId(Long userId);
    public List<Account> findAllByOrderById();
    @Query("SELECT acc FROM Account acc JOIN FETCH acc.user ORDER BY acc.id")
    List<Account> findAllWithUserOrderedById();

    @Query("SELECT acc FROM Account acc JOIN FETCH acc.user u JOIN FETCH acc.role r WHERE r.roleName = :roleName")
    List<Account> findAllByRoleNameWithUser(@Param("roleName") String roleName);
    @Query("SELECT acc FROM Account acc JOIN FETCH acc.user u WHERE u.email = :email")
    List<Account> findAllByEmailWithUser(@Param("email") String email);
    @Query("SELECT acc FROM Account acc JOIN FETCH acc.user u WHERE SOUNDEX(:name) = SOUNDEX(u.firstName) OR SOUNDEX(:name) = SOUNDEX(u.lastName)")
    List<Account> findAllBySimilarNameWithUser(@Param("name") String name);
}
