package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Category;
import org.example.qint_backend.domain.question.domain.Question;
import org.example.qint_backend.domain.question.presentation.dto.request.GetQuestionsByCategoryRequest;
import org.example.qint_backend.domain.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionSearchService {
    private final QuestionRepository questionRepository;
}
