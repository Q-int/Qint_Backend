package org.example.qint_backend.domain.email.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("auth_code")
    private String authCode;

    @Column(nullable = false)
    @JsonProperty("is_verified")
    private Boolean isVerified = false;

    @Column(nullable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public EmailVerification(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
        this.isVerified = false;
        this.createdAt = LocalDateTime.now();
    }

    public void updateAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void verify() {
        this.isVerified = true;
    }
}
