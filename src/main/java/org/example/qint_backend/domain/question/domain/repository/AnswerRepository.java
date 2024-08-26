package org.example.qint_backend.domain.question.domain.repository;

import org.example.qint_backend.domain.question.domain.Answer;
import org.example.qint_backend.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findAllByQuestionAndIsCorrectIsTrue(Question question);
}
