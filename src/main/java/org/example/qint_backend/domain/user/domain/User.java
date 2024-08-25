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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "correctAnswers")
    private Long correctAnswers;

    @Column(name = "incorrectAnswers")
    private Long incorrectAnswers;
}
