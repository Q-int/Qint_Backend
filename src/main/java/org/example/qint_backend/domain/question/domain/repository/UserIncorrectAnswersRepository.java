package org.example.qint_backend.domain.question.domain.repository;

import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.presentation.dto.response.UserIncorrectAnswerElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIncorrectAnswersRepository extends JpaRepository<UserIncorrectAnswers, Long> {
    List<UserIncorrectAnswerElement> findAllByUser(User user);
}
