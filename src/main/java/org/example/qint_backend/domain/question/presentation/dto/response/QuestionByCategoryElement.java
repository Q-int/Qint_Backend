package org.example.qint_backend.domain.question.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionByCategoryElement {

    @JsonProperty("question_id")
    private Long questionId;

    private String contents;

    private List<OptionsElement> options;
}
