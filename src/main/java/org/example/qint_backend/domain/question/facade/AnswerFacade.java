package org.example.qint_backend.domain.question.facade;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Answer;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.repository.AnswerRepository;
import org.example.qint_backend.domain.question.exception.AnswerNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerFacade {

    private final AnswerRepository answerRepository;

    public Answer getAllByQuestionAndAndIsCorrectIsTrue(Question question) {
        return answerRepository.findAllByQuestionAndAndIsCorrectIsTrue(question).orElseThrow(() -> AnswerNotFoundException.EXCEPTION);
    }
}
