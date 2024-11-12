package org.example.qint_backend.domain.email.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CheckAuthCodeRequest {

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "email 형식")
    private String email;

    @NotBlank(message = "공백 또는 띄어쓰기를 허용하지 않습니다.")
    @JsonProperty("auth_code")
    private String authCode;
}
