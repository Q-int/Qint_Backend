package org.example.qint_backend.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @Column(name = "correctAnswers", nullable = false)
    private Long correctAnswers;

    @Column(name = "incorrectAnswers", nullable = false)
    private Long incorrectAnswers;

    @Builder
    public User(String email, String password, Long correctAnswers, Long incorrectAnswer) {
        this.email = email;
        this.password = password;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswer;
    }
}
