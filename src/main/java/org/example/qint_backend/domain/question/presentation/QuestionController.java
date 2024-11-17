package org.example.qint_backend.domain.question.presentation;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.Category;
import org.example.qint_backend.domain.question.presentation.dto.request.AnswerJudgmentRequest;
import org.example.qint_backend.domain.question.presentation.dto.request.MoveToHomeRequest;
import org.example.qint_backend.domain.question.presentation.dto.request.MoveToNextProblemRequest;
import org.example.qint_backend.domain.question.presentation.dto.response.AnswerJudgmentResponse;
import org.example.qint_backend.domain.question.presentation.dto.response.MoveToHomeResponse;
import org.example.qint_backend.domain.question.presentation.dto.response.QuestionByCategoryResponse;
import org.example.qint_backend.domain.question.service.GetJudgmentResultService;
import org.example.qint_backend.domain.question.service.GetQuestionByCategoryService;
import org.example.qint_backend.domain.question.service.MoveToHomeService;
import org.example.qint_backend.domain.question.service.MoveToNextProblemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final GetQuestionByCategoryService getQuestionByCategoryService;
    private final GetJudgmentResultService getJudgmentResultService;
    private final MoveToHomeService moveToHomeService;
    private final MoveToNextProblemService moveToNextProblemService;

    @GetMapping("/categories")
    public QuestionByCategoryResponse getQuestionsByCategory(
            @RequestParam List<Category> categories
    ) {
        return getQuestionByCategoryService.execute(categories);
    }

    @PostMapping("/judge")
    public AnswerJudgmentResponse getJudgementResultService(
            @RequestBody AnswerJudgmentRequest request
    ){
        return getJudgmentResultService.execute(request);
    }
    
    @PostMapping("/move-to-home")
    public MoveToHomeResponse moveToHomeService(@RequestBody MoveToHomeRequest request) {
        return moveToHomeService.execute(request);
    }

    @PostMapping("/move-to-next-problem")
    public void moveToNextProblemService(@RequestBody MoveToNextProblemRequest request) {
        moveToNextProblemService.execute(request);
    }
}
