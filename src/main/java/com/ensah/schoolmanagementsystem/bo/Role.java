package com.ensah.schoolmanagementsystem.bo;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String roleName;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
