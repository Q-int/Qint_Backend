package org.example.qint_backend.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserIncorrectAnswerResponse {

    private final List<UserIncorrectAnswerElement> userIncorrectAnswerElementList;
}
