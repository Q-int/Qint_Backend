package org.example.qint_backend.domain.email.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.email.presentation.dto.request.CheckAuthCodeRequest;
import org.example.qint_backend.domain.email.presentation.dto.request.EmailVerifyRequest;
import org.example.qint_backend.domain.email.presentation.dto.request.SendAuthCodeRequest;
import org.example.qint_backend.domain.email.presentation.dto.response.CheckAuthCodeResponse;
import org.example.qint_backend.domain.email.presentation.dto.response.EmailVerifyResponse;
import org.example.qint_backend.domain.email.service.CheckAuthCodeService;
import org.example.qint_backend.domain.email.service.EmailVerifyService;
import org.example.qint_backend.domain.email.service.SendAuthCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final SendAuthCodeService sendAuthCodeService;

    private final CheckAuthCodeService checkAuthCodeService;

    private final EmailVerifyService emailVerifyService;

    @PostMapping("/send-authcode")
    public void sendAuthCode(@RequestBody @Valid SendAuthCodeRequest sendAuthCodeRequest) {
        sendAuthCodeService.execute(sendAuthCodeRequest);
    }

    @PostMapping("/check-authcode")
    public CheckAuthCodeResponse checkAuthCode(@RequestBody @Valid CheckAuthCodeRequest checkAuthCodeRequest) {
        return checkAuthCodeService.execute(checkAuthCodeRequest);
    }

    @PostMapping("/email/email-verify")
    public EmailVerifyResponse EmailVerify(@RequestBody @Valid EmailVerifyRequest emailVerifyRequest) {
        return emailVerifyService.execute(emailVerifyRequest);
    }
}
