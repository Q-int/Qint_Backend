package org.example.qint_backend.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;

//merge 전 삭제할 것

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "tbl_user")
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

    public void updateIncorrectAnswersCounts(Long incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void updateCorrectAnswersCounts(Long correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void resetAnswersCounts() {
        this.correctAnswers = 0L;
        this.incorrectAnswers = 0L;
    }
}
