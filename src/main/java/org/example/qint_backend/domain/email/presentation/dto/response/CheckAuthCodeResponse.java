package org.example.qint_backend.domain.email.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckAuthCodeResponse {

    private Boolean isVerified;
}
