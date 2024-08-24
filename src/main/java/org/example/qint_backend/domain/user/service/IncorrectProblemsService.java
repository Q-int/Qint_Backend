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

        return userIncorrectAnswers.stream().map(userIncorrectAnswers1 -> UserIncorrectAnswerResponse.builder()
                .contents(userIncorrectAnswers1.getQuestion().getContents())
                .commentary(userIncorrectAnswers1.getQuestion().getCommentary())
                .incorrectAnswer(userIncorrectAnswers1.getAnswer().getText())
                .correctAnswer(
                        answerFacade.getAllByQuestionAndAndIsCorrectIsTrue(userIncorrectAnswers1.getQuestion())
                                .getText()
                )
                .build()).toList();
    }

}
