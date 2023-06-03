package com.ensah.schoolmanagementsystem.bo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "account")
    private User user;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    private boolean enabled = true;
    private boolean expired = false;
    private boolean locked = false;
    private boolean isCredentialsExpired = false;
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private Date createdAt;
    private Date deletedAt = null;

    public void setId(Long idAccount) {
        this.id = idAccount;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCredentialsExpired() {
        return isCredentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        isCredentialsExpired = credentialsExpired;
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", expired=" + expired +
                ", locked=" + locked +
                ", isCredentialsExpired=" + isCredentialsExpired +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
