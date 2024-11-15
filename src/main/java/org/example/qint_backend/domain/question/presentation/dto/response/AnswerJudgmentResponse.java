package org.example.qint_backend.domain.question.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerJudgmentResponse {

    @JsonProperty("answer_text")
    private String answerText;

    private String commentary;

    @JsonProperty("is_correct")
    private Boolean isCorrect;
}