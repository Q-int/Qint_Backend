package org.example.qint_backend.domain.question.presentation;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.presentation.dto.request.CategoryRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryResponse;
import org.example.qint_backend.domain.question.service.GetQuestionByCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class QuestionController {

    private final GetQuestionByCategoryService getQuestionByCategoryService;

    @GetMapping
    public QuestionByCategoryResponse getQuestionsByCategory(
            @RequestParam(name = "categorys") CategoryRequest request
    ) {
        return getQuestionByCategoryService.execute(request);
    }
}
