package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.presentation.dto.request.MoveToHomeRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.MoveToHomeResponse;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MoveToHomeService {

    private final UserFacade userFacade;

    @Transactional
    public MoveToHomeResponse execute(MoveToHomeRequest request) {
        User user = userFacade.getCurrentUser();

        Long correctAnswers = user.getCorrectAnswers();
        Long incorrectAnswers = user.getIncorrectAnswers();

        user.resetAnswersCounts(correctAnswers, incorrectAnswers);

        return MoveToHomeResponse.builder()
                .correctAnswers(correctAnswers)
                .incorrectAnswers(incorrectAnswers)
                .build();
    }
}
