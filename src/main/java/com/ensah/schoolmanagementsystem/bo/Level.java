package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "please provide an alias")
    private String alias;
    @NotBlank(message = "please provide a title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Set<Inscription> inscriptions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Set<CourseModule> CourseModules = new HashSet<>();

    public Level() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Level(String alias, String title) {
        this.alias = alias;
        this.title = title;
    }

    public Set<CourseModule> getCourseModules() {
        return CourseModules;
    }

    public void setCourseModules(Set<CourseModule> courseModules) {
        CourseModules = courseModules;
    }

}
