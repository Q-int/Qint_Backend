package org.example.qint_backend.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAnswersResponse {

    private Long correct_answers;

    private Long incorrect_answers;

}
