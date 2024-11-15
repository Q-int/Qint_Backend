package org.example.qint_backend.domain.email.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String auth_code;

    @Column(nullable = false)
    private Boolean is_verified = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public EmailVerification(String email, String auth_code) {
        this.email = email;
        this.auth_code = auth_code;
        this.is_verified = false;
        this.createdAt = LocalDateTime.now();
    }

    public void updateAuthCode(String authCode) {
        this.auth_code = authCode;
    }

    public void verify() {
        this.is_verified = true;
    }

    public boolean get_is_verified() {
        return false;
    }
}
