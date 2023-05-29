package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class JustifyingDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "please provide the path of the justifying document file")
    private String file;
    @NotBlank(message = "please specify a title")
    private String title;
    @NotBlank(message = "please specify the receiving date")
    private Date receivedAt;
    @NotBlank(message = "please specify the state of the document")
    private Boolean state;

    @ManyToMany(mappedBy = "justifyingDocuments")
    private Set<Absence> absences = new HashSet<>();
    public JustifyingDocument() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public JustifyingDocument(String file, String title, Date receivedAt, Boolean state) {
        this.file = file;
        this.title = title;
        this.receivedAt = receivedAt;
        this.state = state;
    }
}
