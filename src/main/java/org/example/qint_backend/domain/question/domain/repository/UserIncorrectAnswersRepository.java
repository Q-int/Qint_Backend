package org.example.qint_backend.domain.question.domain.repository;

import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.qint_backend.domain.user.domain.User;
import java.util.List;

@Repository
public interface UserIncorrectAnswersRepository extends JpaRepository<UserIncorrectAnswers, Long> {
    List<UserIncorrectAnswers> findAllByUser(User user);
}
