package com.opensourcebim.bcfserver.models;

import com.opensourcebim.bcfserver.models.enums.UserType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uoid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String token;

    private UserType userType;

    private UserLog

    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User() {}
}
