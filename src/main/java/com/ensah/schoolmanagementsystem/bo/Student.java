package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@DiscriminatorValue("student")
public class Student extends User{
    @NotBlank(message = "please provide a cne")
    @Pattern(regexp = "^[A-Za-z]{1}\\d{9}$", message = "Wrong Cne format)")
    private String cne;

    @NotNull(message = "Please provide a birthdate")
    private Date birthDate;
}
