package org.example.qint_backend.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("correct_answers")
    private Long correctAnswers;

    @Column(name = "incorrectAnswers", nullable = false)
    @JsonProperty("incorrect_answers")
    private Long incorrectAnswers;
}
