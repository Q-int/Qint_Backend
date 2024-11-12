package org.example.qint_backend.domain.question.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AnswerJudgmentRequest {

    @JsonProperty("question_id")
    private Long questionId;
    @JsonProperty("answer_id")
    private Long answerId;
}
