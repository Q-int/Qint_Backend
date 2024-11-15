package org.example.qint_backend.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserIncorrectAnswerElement {

    private String contents;

    private String commentary;

    @JsonProperty("incorrect_answer")
    private String incorrectAnswer;

    @JsonProperty("correct_answer")
    private String correctAnswer;
}
