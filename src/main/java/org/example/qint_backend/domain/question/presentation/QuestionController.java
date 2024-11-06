package org.example.qint_backend.domain.question.presentation;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.presentation.dto.request.AnswerJudgmentRequest;
import org.example.qint_backend.domain.question.presentation.dto.request.CategoryRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.AnswerJudgmentResponse;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryResponse;
import org.example.qint_backend.domain.question.service.GetJudgmentResultService;
import org.example.qint_backend.domain.question.service.GetQuestionByCategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final GetQuestionByCategoryService getQuestionByCategoryService;
    private final GetJudgmentResultService getJudgmentResultService;

    @GetMapping
    public QuestionByCategoryResponse getQuestionsByCategory(
            @RequestBody CategoryRequest request
    ) {
        return getQuestionByCategoryService.execute(request);
    }

    @PostMapping("/judge")
    public AnswerJudgmentResponse getJudgementResultService(
            @RequestBody AnswerJudgmentRequest request
    ){
        return getJudgmentResultService.execute(request);
    }

}
