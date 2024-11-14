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
        Long questionId = answerJudgmentRequest.getQuestion_id();
        Long answerId = answerJudgmentRequest.getAnswer_id();

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> InvalidQuestionIdException.EXCEPTION);

        Answer submittedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> InvalidAnswerIdException.EXCEPTION);

        Answer correctAnswer = answerFacade.getFindByQuestionIdAndIsCorrectIsTrue(questionId);

        boolean isCorrect = submittedAnswer.getId().equals(correctAnswer.getId());

        if (!isCorrect) {
            saveUserIncorrectAnswer(question, submittedAnswer);
        }

        return AnswerJudgmentResponse.builder()
                .answer_text(question.getContents())
                .commentary(question.getCommentary())
                .is_correct(isCorrect)
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