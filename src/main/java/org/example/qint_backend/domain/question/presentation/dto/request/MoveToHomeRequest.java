package org.example.qint_backend.domain.question.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MoveToHomeRequest {
    @JsonProperty("move_to_home")
    private boolean moveToHome;
}
