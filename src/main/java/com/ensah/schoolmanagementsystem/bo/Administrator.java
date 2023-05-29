package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(name="administratorId")
public class Administrator extends User{
    @NotBlank(message = "Please provide an administrative grade")
    private String grade;

    public Administrator(String firstName, String lastName, String username, String email, String phone, String arabicFirstName, String arabicLastName, String picture, String grade) {
        super(firstName, lastName, username, email, phone, arabicFirstName, arabicLastName, picture);
        this.grade = grade;
    }

    public Administrator(String grade) {
        this.grade = grade;
    }

    public Administrator() {

    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
