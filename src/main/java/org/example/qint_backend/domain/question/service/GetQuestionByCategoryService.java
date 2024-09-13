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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public QuestionByCategoryResponse execute(CategoryRequest request) {
        List<String> categories = request.getCategories();
        List<Question> allQuestions = new ArrayList<>();
        List<Question> examQuestions = new ArrayList<>();

        for (String category : categories) {
            Category categoryEnum = Category.valueOf(category);

            examQuestions.addAll(userIncorrectAnswersRepository.findAllByQuestionCategory(categoryEnum)
                    .stream()
                    .map(UserIncorrectAnswers::getQuestion)
                    .toList());

            allQuestions.addAll(questionRepository.findAllByCategory(categoryEnum));

            for (Question question : allQuestions) {
                if(!examQuestions.contains(question)) {
                    examQuestions.add(question);
                }
            }
        }

        examQuestions = examQuestions.stream()
                .limit(MAX_QUESTIONS)
                .collect(Collectors.toList());

        Collections.shuffle(examQuestions);

        List<QuestionByCategoryElement> questionByCategoryElements = examQuestions.stream()
                .map(question -> {
                    List<OptionsElement> optionsElements = answerRepository.findAllByQuestion(question).stream()
                            .map(answer -> new OptionsElement(answer.getId(), answer.getText()))
                            .collect(Collectors.toList());

                    return new QuestionByCategoryElement(question.getId(), question.getContents(), optionsElements);
                })
                .collect(Collectors.toList());

        return new QuestionByCategoryResponse(questionByCategoryElements);
    }
}
