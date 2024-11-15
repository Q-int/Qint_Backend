package org.example.qint_backend.domain.question.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AnswerJudgmentRequest {

    private Long questionId;

    private Long answerId;
}
