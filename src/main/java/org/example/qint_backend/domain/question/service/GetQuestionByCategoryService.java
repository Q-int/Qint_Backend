package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Category;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.domain.UserIncorrectAnswers;
import org.example.qint_backend.domain.question.domain.repository.AnswerRepository;
import org.example.qint_backend.domain.question.domain.repository.QuestionRepository;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.example.qint_backend.domain.question.presentation.dto.response.OptionsElement;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryElement;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryResponse;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetQuestionByCategoryService {

    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserFacade userFacade;

    private static final int MAX_QUESTIONS = 15;

    @Transactional
    public QuestionByCategoryResponse execute(List<Category> categories) {
        List<Question> allQuestions = new ArrayList<>();
        List<Question> examQuestions = new ArrayList<>();

        User user = userFacade.getCurrentUser();
        user.resetAnswersCounts();

        for (Category category : categories) {
            Category categoryEnum = Category.valueOf(String.valueOf(category));

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
                            .map(OptionsElement::of)
                            .toList();

                    return new QuestionByCategoryElement(question.getId(), question.getContents(), optionsElements);
                })
                .toList();

        return new QuestionByCategoryResponse(questionByCategoryElements);
    }
}
