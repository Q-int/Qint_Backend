package org.example.qint_backend.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserIncorrectAnswerResponse {
    @JsonProperty("user_incorrect_answers_element_list")
    private final List<UserIncorrectAnswerElement> userIncorrectAnswerElementList;
}
