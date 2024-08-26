package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Queue;

@Getter
@AllArgsConstructor
public class QuestionByCategoryResponse {
    private final List<QuestionElement> questions;
}