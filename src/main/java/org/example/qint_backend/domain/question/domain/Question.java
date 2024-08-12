package org.example.qint_backend.domain.question.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Immutable
@Table(name = "tbl_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "contents", length = 255, nullable = false, updatable = false)
    private String contents;

    @Column(name = "comentary", length = 255, nullable = false, updatable = false)
    private String commentary;

    @Column(name = "category", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
}