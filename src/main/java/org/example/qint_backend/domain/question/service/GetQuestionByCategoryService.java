package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Category;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.example.qint_backend.domain.question.domain.repository.AnswerRepository;
import org.example.qint_backend.domain.question.domain.repository.QuestionRepository;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.presentation.dto.request.CategoryRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.OptionsElement;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryElement;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetQuestionByCategoryService {

    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    private static final int MAX_QUESTIONS = 15;

    public QuestionByCategoryResponse execute(CategoryRequest categoryRequest) {
        List<String> categorys = categoryRequest.getCategorys();
        List<Question> allQuestions = new ArrayList<>();
        List<Question> incorrectQuestions = new ArrayList<>();

        for (String category : categorys) {
            incorrectQuestions.addAll(userIncorrectAnswersRepository.findAllByQuestionCategory(Category.valueOf(category))
                    .stream()
                    .map(UserIncorrectAnswers::getQuestion)
                    .toList());

            allQuestions.addAll(questionRepository.findAllByCategory(Category.valueOf(category)));
        }

        allQuestions.removeAll(incorrectQuestions);

        Collections.shuffle(incorrectQuestions);
        Collections.shuffle(allQuestions);

        incorrectQuestions.addAll(allQuestions);

        incorrectQuestions = incorrectQuestions.stream()
                .limit(MAX_QUESTIONS)
                .collect(Collectors.toList());

        Collections.shuffle(incorrectQuestions);

        return QuestionByCategoryResponse.builder()
                .questions(incorrectQuestions.stream()
                        .map(question -> QuestionByCategoryElement.builder()
                                .question_id(question.getId())
                                .contents(question.getContents())
                                .options(answerRepository.findAllByQuestion(question).stream()
                                        .map(answer -> OptionsElement.builder()
                                                .answerId(answer.getId())
                                                .text(answer.getText())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
