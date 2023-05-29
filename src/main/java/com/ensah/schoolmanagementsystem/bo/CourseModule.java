package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CourseModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "please provide a title")
    private String title;
    @NotBlank(message = "please provide a code")
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseModule_id",nullable = false)
    private Set<Course> Courses = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public CourseModule(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public CourseModule() {
    }
}
