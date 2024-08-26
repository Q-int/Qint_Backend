package org.example.qint_backend.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserIncorrectAnswerResponse {
    List<UserIncorrectAnswerElement> userIncorrectAnswerList;
}
