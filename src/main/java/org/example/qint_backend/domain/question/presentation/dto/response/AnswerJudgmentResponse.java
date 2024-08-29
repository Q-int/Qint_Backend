package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerJudgmentResponse {
    private String answerText; //문제의 정답
    private String commentary; //해설
    private Boolean isCorrect; //채점 결과
}