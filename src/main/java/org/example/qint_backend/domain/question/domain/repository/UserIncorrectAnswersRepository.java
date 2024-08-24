package org.example.qint_backend.domain.question.domain.repository;

import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.example.qint_backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIncorrectAnswersRepository extends JpaRepository<UserIncorrectAnswers, Long> {

    List<UserIncorrectAnswers> findAllByUser(User user);
}
