package org.example.qint_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.user.presentation.dto.response.UserAnswersResponse;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserFacade userFacade;

    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;

    public UserAnswersResponse execute() {
        User user = userFacade.getCurrentUser();

        return UserAnswersResponse.builder()
                .correctAnswers(user.getCorrectAnswers())
                .incorrectAnswers(user.getIncorrectAnswers())
                .build();
    }

}
