package org.example.qint_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.facade.AnswerFacade;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.example.qint_backend.domain.user.presentation.dto.response.UserIncorrectAnswerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncorrectProblemsService {

    private final UserFacade userFacade;

    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;

    private final AnswerFacade answerFacade;

    public List<UserIncorrectAnswerResponse> execute() {
        User user = userFacade.getCurrentUser();

        List<UserIncorrectAnswers> userIncorrectAnswers = userIncorrectAnswersRepository.findAllByUser(user);

        return userIncorrectAnswers.stream().map(incorrectAnswers -> UserIncorrectAnswerResponse.builder()
                .contents(incorrectAnswers.getQuestion().getContents())
                .commentary(incorrectAnswers.getQuestion().getCommentary())
                .incorrectAnswer(incorrectAnswers.getAnswer().getText())
                .correctAnswer(
                        answerFacade.getAllByQuestionAndAndIsCorrectIsTrue(incorrectAnswers.getQuestion())
                                .getText()
                )
                .build()).toList();
    }

}
