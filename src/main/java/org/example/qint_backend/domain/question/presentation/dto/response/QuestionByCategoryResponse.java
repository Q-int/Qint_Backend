package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionByCategoryResponse {
    List<QuestionByCategoryElement> questions;

    public QuestionByCategoryResponse(List<QuestionByCategoryElement> questions) {
        this.questions = questions;
    }
}
