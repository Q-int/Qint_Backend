package org.example.qint_backend.domain.question.domain.repository;

import org.example.qint_backend.domain.question.domain.Answer;
import org.example.qint_backend.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestion(Question question);
    Optional<Answer> findAllByQuestionAndIsCorrectIsTrue(Question question);
    Optional<Answer> findByQuestionIdAndIsCorrectIsTrue(Long questionId);
}