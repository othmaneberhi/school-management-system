package com.ensah.schoolmanagementsystem.bo;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "please provide a year")
    @Digits(integer = 4, fraction = 0,message = "invalid year format")
    @Range(min = 1900, max = 2100,message = "invalid year")
    private Integer year;

    @NotNull
    private Boolean state;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inscription_id")
    private Set<Absence> absences = new HashSet<>();

    public Inscription() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public Inscription(Integer year, Boolean state) {
        this.year = year;
        this.state = state;
    }
}
