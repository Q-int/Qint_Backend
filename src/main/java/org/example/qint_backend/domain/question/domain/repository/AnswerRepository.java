package org.example.qint_backend.domain.question.domain.repository;

import org.example.qint_backend.domain.question.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}