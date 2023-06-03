package com.ensah.schoolmanagementsystem.bo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name="studentId")
public class Student extends User{
    @NotBlank(message = "please provide a cne")
    @Pattern(regexp = "^[A-Za-z]{1}\\d{9}$", message = "Wrong Cne format")
    private String cne;

    @NotNull(message = "Please provide a birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inscription_id")
    private Inscription inscription;

    public Student() {

    }


    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public Student(String firstName, String lastName, String email, String phone, String arabicFirstName, String arabicLastName, String picture, String cne, Date birthDate) {
        super(firstName, lastName, email, phone, arabicFirstName, arabicLastName, picture);
        this.cne = cne;
        this.birthDate = birthDate;
    }

    public Student(String cne, Date birthDate, Inscription inscription) {
        this.cne = cne;
        this.birthDate = birthDate;
        this.inscription = inscription;
    }
}
