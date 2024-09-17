package org.example.qint_backend.domain.question.presentation.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CategoryRequest {
    public List<String> categories;
}
