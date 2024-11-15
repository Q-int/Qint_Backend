package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Answer;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.example.qint_backend.domain.question.domain.repository.AnswerRepository;
import org.example.qint_backend.domain.question.domain.repository.QuestionRepository;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.exception.InvalidAnswerIdException;
import org.example.qint_backend.domain.question.exception.InvalidQuestionIdException;
import org.example.qint_backend.domain.question.facade.AnswerFacade;
import org.example.qint_backend.domain.question.presentation.dto.request.AnswerJudgmentRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.AnswerJudgmentResponse;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetJudgmentResultService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;
    private final AnswerFacade answerFacade;
    private final UserFacade userFacade;

    public AnswerJudgmentResponse execute(AnswerJudgmentRequest answerJudgmentRequest) {
        Long questionId = answerJudgmentRequest.getQuestionId();
        Long answerId = answerJudgmentRequest.getAnswerId();

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> InvalidQuestionIdException.EXCEPTION);

        Answer submittedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> InvalidAnswerIdException.EXCEPTION);

        Answer correctAnswer = answerFacade.getFindByQuestionIdAndIsCorrectIsTrue(questionId);

        boolean isCorrect = submittedAnswer.getId().equals(correctAnswer.getId());

        if (!isCorrect) {
            if (!userIncorrectAnswersRepository.existsByQuestionId(questionId)) {
                saveUserIncorrectAnswer(question, submittedAnswer);
            }
        }

        return AnswerJudgmentResponse.builder()
                .answerText(correctAnswer.getText())
                .commentary(question.getCommentary())
                .isCorrect(isCorrect)
                .build();
    }

    private void saveUserIncorrectAnswer(Question question, Answer submittedAnswer){
        User user = userFacade.getCurrentUser();
        userIncorrectAnswersRepository.save(
                UserIncorrectAnswers.builder()
                        .user(user)
                        .question(question)
                        .answer(submittedAnswer)
                        .build()
        );
    }
}