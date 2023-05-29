package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "please provide an absence start date")
    private Date absenceStartDate;
    @NotNull(message = "please provide an absence end date")
    private Date absenceEndDate;
    @NotNull
    private Boolean state;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "justifyingDocument_absence",
            joinColumns = @JoinColumn(name = "absence_id"),
            inverseJoinColumns = @JoinColumn(name="justifyingDocument_id")
    )
    private Set<JustifyingDocument> justifyingDocuments = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getAbsenceStartDate() {
        return absenceStartDate;
    }

    public void setAbsenceStartDate(Date absenceStartDate) {
        this.absenceStartDate = absenceStartDate;
    }

    public Date getAbsenceEndDate() {
        return absenceEndDate;
    }

    public void setAbsenceEndDate(Date absenceEndDate) {
        this.absenceEndDate = absenceEndDate;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
