package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE user SET deleted_at=CURRENT_TIMESTAMP id=?")
@Where(clause = "deleted_at is null")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;
    @NotBlank(message = "Please enter a CIN")
    @Pattern(regexp = "^[A-Za-z]{2}\\d{6}$", message = "Wrong CIN format")
    private String cin;
    @NotBlank(message = "Please enter a firstname")
    private String firstName;
    @NotBlank(message = "Please enter a lastname")
    private String lastName;
    @Email(message = "Please provide a valid email")
    @NotNull(message = "Please enter an email")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Please enter a phone number")
    @Pattern(regexp = "^06\\d{8}$", message = "Wrong phone number format, expected (06 ## ## ## ##)")
    private String phone;
    @NotBlank(message = "Please enter an arabic firstname")
    @Pattern(regexp = "\\p{InArabic}+", message = "This field must be in Arabic")
    private String arabicFirstName;
    @NotBlank(message = "Please enter an arabic lastname")
    @Pattern(regexp = "\\p{InArabic}+", message = "This field must be in Arabic")
    private String arabicLastName;
    private String picture;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private Date createdAt;

    private Date deletedAt = null;


    public User(String firstName, String lastName, String email, String phone, String arabicFirstName, String arabicLastName, String picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.arabicFirstName = arabicFirstName;
        this.arabicLastName = arabicLastName;
        this.picture = picture;
    }

    public User() {

    }

    public void setId(Long idUser) {
        this.id = idUser;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArabicFirstName() {
        return arabicFirstName;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getArabicLastName() {
        return arabicLastName;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
