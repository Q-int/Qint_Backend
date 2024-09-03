package org.example.qint_backend.domain.user.domain;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;

//merge 전 삭제할 것

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "tbl_user")
public class User {
=======
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_user")
public class User {

>>>>>>> 47500e0c830671dcf427f0336dccdd0d9f7d5f7a
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "correctAnswers")
    private Long correctAnswers;

    @Column(name = "incorrectAnswers")
    private Long incorrectAnswers;
=======
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
>>>>>>> 47500e0c830671dcf427f0336dccdd0d9f7d5f7a
}
