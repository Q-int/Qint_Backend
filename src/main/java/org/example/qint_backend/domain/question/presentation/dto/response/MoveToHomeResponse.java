package org.example.qint_backend.domain.question.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MoveToHomeResponse {

    @JsonProperty("correct_answers")
    private Long correctAnswers;

    @JsonProperty("incorrect_answers")
    private Long incorrectAnswers;
}
