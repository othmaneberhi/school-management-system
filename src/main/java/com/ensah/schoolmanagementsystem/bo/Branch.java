package com.ensah.schoolmanagementsystem.bo;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "please provide a title")
    private String title;
    @NotBlank(message = "please provide a code")
    private String code;

    @NotNull(message = "please provide a year")
    @Digits(integer = 4, fraction = 0,message = "invalid year format")
    @Range(min = 1900, max = 2100,message = "invalid year")
    private Integer accreditationYear;
    @NotNull(message = "please provide a year")
    @Digits(integer = 4, fraction = 0,message = "invalid year format")
    @Range(min = 1900, max = 2100,message = "invalid year")
    private Integer accreditationEndYear;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Set<Level> levels = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Set<Coordination> coordinations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAccreditationYear() {
        return accreditationYear;
    }

    public void setAccreditationYear(Integer accreditationYear) {
        this.accreditationYear = accreditationYear;
    }

    public Integer getAccreditationEndYear() {
        return accreditationEndYear;
    }

    public void setAccreditationEndYear(Integer accreditationEndYear) {
        this.accreditationEndYear = accreditationEndYear;
    }

    public Set<Level> getLevels() {
        return levels;
    }

    public void setLevels(Set<Level> levels) {
        this.levels = levels;
    }

    public Set<Coordination> getCoordinations() {
        return coordinations;
    }

    public void setCoordinations(Set<Coordination> coordinations) {
        this.coordinations = coordinations;
    }

    public Branch(String title, String code, Integer accreditationYear, Integer accreditationEndYear) {
        this.title = title;
        this.code = code;
        this.accreditationYear = accreditationYear;
        this.accreditationEndYear = accreditationEndYear;
    }
}
