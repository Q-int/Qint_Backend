package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerJudgmentResponse {
    private String answerText;
    private String commentary;
    private Boolean isCorrect;
}