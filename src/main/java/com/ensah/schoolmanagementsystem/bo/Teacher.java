package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="teacherId")
public class Teacher extends User{
    @NotBlank(message = "please provide a specialization")
    private String specialization;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Set<Coordination> coordinations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Set<Absence> absences = new HashSet<>();


    public Teacher() {

    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Teacher(String firstName, String lastName, String username, String email, String phone, String arabicFirstName, String arabicLastName, String picture, String specialization) {
        super(firstName, lastName, username, email, phone, arabicFirstName, arabicLastName, picture);
        this.specialization = specialization;
    }

    public Teacher(String specialization) {
        this.specialization = specialization;
    }

    public Set<Coordination> getCoordinations() {
        return coordinations;
    }

    public void setCoordinations(Set<Coordination> coordinations) {
        this.coordinations = coordinations;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }
}
