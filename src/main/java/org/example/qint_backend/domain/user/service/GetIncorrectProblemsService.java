package org.example.qint_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.facade.AnswerFacade;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.example.qint_backend.domain.user.presentation.dto.response.UserIncorrectAnswerElement;
import org.example.qint_backend.domain.user.presentation.dto.response.UserIncorrectAnswerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetIncorrectProblemsService {

    private final UserFacade userFacade;

    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;

    private final AnswerFacade answerFacade;

    public UserIncorrectAnswerResponse execute() {

        User user = userFacade.getCurrentUser();

        List<UserIncorrectAnswerElement> userIncorrectAnswerElementList = userIncorrectAnswersRepository.findAllByUser(user)
                .stream().map(userIncorrectAnswerElement ->
                        UserIncorrectAnswerElement.builder()
                            .contents(userIncorrectAnswerElement.getQuestion().getContents())
                            .commentary(userIncorrectAnswerElement.getQuestion().getCommentary())
                            .incorrect_Answer(userIncorrectAnswerElement.getAnswer().getText())
                            .correct_Answer(answerFacade.getAllByQuestionAndIsCorrectIsTrue(userIncorrectAnswerElement.getQuestion()).getText())
                            .build()
                ).toList();
        return new UserIncorrectAnswerResponse(userIncorrectAnswerElementList);
    }

}
