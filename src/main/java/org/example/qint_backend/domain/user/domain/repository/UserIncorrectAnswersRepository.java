package org.example.qint_backend.domain.user.domain.repository;

import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIncorrectAnswersRepository extends JpaRepository<UserIncorrectAnswers, Long> {
}
