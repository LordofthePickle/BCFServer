package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;
import com.opensourcebim.bcfserver.utils.PasswordResetTokenUtils;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passResetTokenOid;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    public PasswordResetToken() {

    }

    public PasswordResetToken(User user) {
        this.token = PasswordResetTokenUtils.generateToken();
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusMinutes(15);
    }

    public Long getPassResetTokenOid() {
        return passResetTokenOid;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
