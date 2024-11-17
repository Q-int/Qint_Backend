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

    private static final int MAX_ANSWER_COUNT = 15;

    public void execute(MoveToNextProblemRequest request) {
        User user = userFacade.getCurrentUser();

        Long correctAnswers = user.getCorrectAnswers();
        Long incorrectAnswers = user.getIncorrectAnswers();

        long sumAnswers = correctAnswers + incorrectAnswers;

        boolean moveToNextProblem = request.isMoveToNextProblem();

        if (sumAnswers == MAX_ANSWER_COUNT) {
            user.resetAnswersCounts(correctAnswers, incorrectAnswers);
        }

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
