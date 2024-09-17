package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Answer;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.repository.AnswerRepository;
import org.example.qint_backend.domain.question.domain.repository.QuestionRepository;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.facade.AnswerFacade;
import org.example.qint_backend.domain.question.presentation.dto.request.AnswerJudgmentRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.AnswerJudgmentResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetJudgmentResultService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;
    private final AnswerFacade answerFacade;

    public AnswerJudgmentResponse excute(AnswerJudgmentRequest answerJudgmentRequest) {
        Long questionId = answerJudgmentRequest.getQuestionId();
        Long answerId = answerJudgmentRequest.getAnswerId();

        if(questionId == null && answerId == null){
            //오답 문제 저장 함수 호출
        }

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID"));

        Answer submittedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid answer ID"));

        Answer correctAnswer = answerFacade.getFindByQuestionIdAndIsCorrectIsTrue(questionId);

        boolean isCorrect = submittedAnswer.getId().equals(correctAnswer.getId());

        if (!isCorrect) {
            //오답 문제 저장 함수 호출
        }

        return AnswerJudgmentResponse.builder()
                .answerText(question.getContents())
                .commentary(question.getCommentary())
                .isCorrect(isCorrect)
                .build();
    }
}