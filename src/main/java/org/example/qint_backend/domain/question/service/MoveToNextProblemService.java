package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.presentation.dto.request.MoveToNextProblemRequest;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.domain.repository.UserRepository;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveToNextProblemService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public void execute(MoveToNextProblemRequest request) {

        boolean moveToNextProblem = request.isMoveToNextProblem();

        if (!moveToNextProblem) {
            saveUserIncorrectAnswer();
        }
    }

    private void saveUserIncorrectAnswer() {
        User user = userFacade.getCurrentUser();
        Long incorrectAnswers = user.getIncorrectAnswers();
        user.updateIncorrectAnswersCounts(incorrectAnswers + 1);
        userRepository.save(user);
    }
}
