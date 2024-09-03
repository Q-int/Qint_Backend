package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionByCategoryResponse {
    List<QuestionByCategoryElement> questions;
}
