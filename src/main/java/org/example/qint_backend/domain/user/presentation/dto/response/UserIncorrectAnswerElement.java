package org.example.qint_backend.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserIncorrectAnswerElement {

    private String contents;

    private String commentary;

    private String incorrectAnswer;

    private String correctAnswer;
}
