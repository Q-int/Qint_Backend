package org.example.qint_backend.domain.question.repository;

import org.example.qint_backend.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
