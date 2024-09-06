package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Answer;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.repository.AnswerRepository;
import org.example.qint_backend.domain.question.domain.repository.QuestionRepository;
import org.example.qint_backend.domain.question.facade.AnswerFacade;
import org.example.qint_backend.domain.question.presentation.dto.request.AnswerJudgmentRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.AnswerJudgmentResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetJudgmentResultService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerFacade answerFacade;

    public AnswerJudgmentResponse excute(AnswerJudgmentRequest answerJudgmentRequest) {
        Long questionId = answerJudgmentRequest.getQuestionId();
        Long answerId = answerJudgmentRequest.getAnswerId();

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID"));

        Answer submittedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid answer ID"));

        boolean isSubmittedAnswerBelongsToQuestion = submittedAnswer.getQuestion().getId().equals(questionId);

        if (!isSubmittedAnswerBelongsToQuestion) {
            throw new IllegalArgumentException("question not found");
        }

        Answer correctAnswer = answerRepository.findAll().stream()
                .filter(answer -> answer.getQuestion().getId().equals(questionId))
                .filter(Answer::getIsCorrect)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("correct answer not found"));

        boolean isCorrect = submittedAnswer.getId().equals(correctAnswer.getId());

        return AnswerJudgmentResponse.builder()
                .answerText(question.getContents())
                .commentary(question.getCommentary())
                .isCorrect(isCorrect)
                .build();
    }
}