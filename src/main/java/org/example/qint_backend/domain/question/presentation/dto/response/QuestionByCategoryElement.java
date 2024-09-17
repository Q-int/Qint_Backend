package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionByCategoryElement {
    private Long questionId;
    private String contents;
    private List<OptionsElement> options;

    public QuestionByCategoryElement(Long questionId, String contents, List<OptionsElement> options) {
        this.questionId = questionId;
        this.contents = contents;
        this.options = options;
    }
}
