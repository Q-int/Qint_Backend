package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionByCategoryElement {
    private Long question_id;
    private String contents;
    private List<OptionsElement> options;
}
