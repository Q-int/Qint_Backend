package org.example.qint_backend.domain.question.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MoveToNextProblemRequest {
    @JsonProperty("move_to_Next_Problem")
    private boolean moveToNextProblem;
}
