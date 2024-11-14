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
    private Boolean isVerified = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public EmailVerification(String email, String auth_code) {
        this.email = email;
        this.auth_code = auth_code;
        this.isVerified = false;
        this.createdAt = LocalDateTime.now();
    }

    public void updateAuthCode(String authCode) {
        this.auth_code = authCode;
    }

    public void verify() {
        this.isVerified = true;
    }
}
