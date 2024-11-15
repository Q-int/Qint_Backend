package org.example.qint_backend.domain.email.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckAuthCodeResponse {

    @JsonProperty("is_verified")
    private Boolean isVerified;
}
