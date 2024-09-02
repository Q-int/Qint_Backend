package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.presentation.dto.request.AnswerJudgmentRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.AnswerJudgmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetJudgmentResultService {
    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;

    public AnswerJudgmentResponse excute(AnswerJudgmentRequest answerJudgmentRequest){
        Long questionId = answerJudgmentRequest.getQuestionId();
        Long answerId = answerJudgmentRequest.getAnswerId();
        return null;
    }
}