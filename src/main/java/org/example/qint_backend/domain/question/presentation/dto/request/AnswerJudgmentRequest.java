package org.example.qint_backend.domain.question.presentation.dto.request;

import lombok.Getter;

@Getter
public class AnswerJudgmentRequest {

    private Long question_id;
    private Long answer_id;
}
