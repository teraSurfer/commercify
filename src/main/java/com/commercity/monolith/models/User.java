package com.commercity.monolith.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "COMMERCIFY_USER")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private long uid;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Column
    private UserRoles role;

    @PrePersist
    public void beforeCreate() {
        long now = Calendar.getInstance().getTimeInMillis();
        this.createdAt = new Timestamp(now);
        this.updatedAt = new Timestamp(now);
        this.hashPassword();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    private void hashPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        this.password = passwordEncoder.encode(this.password);
    }

    public boolean verifyPassword(String plainPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return passwordEncoder.matches(this.password, plainPassword);
    }
}
