package org.example.qint_backend.domain.email.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.email.domain.EmailVerification;
import org.example.qint_backend.domain.email.facade.EmailVerificationFacade;
import org.example.qint_backend.domain.email.presentation.dto.request.CheckAuthCodeRequest;
import org.example.qint_backend.domain.email.presentation.dto.response.CheckAuthCodeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckAuthCodeService {

    private final EmailVerificationFacade emailVerificationFacade;

    @Transactional
    public CheckAuthCodeResponse execute(CheckAuthCodeRequest checkAuthCodeRequest) {
        EmailVerification emailVerification = emailVerificationFacade.getEmailVerificationByEmail(checkAuthCodeRequest.getEmail());

        if (emailVerification.getAuthCode().equals(checkAuthCodeRequest.getAuthCode())) {
            emailVerification.verify();
            return CheckAuthCodeResponse.builder().isVerified(true).build();
        }

        return CheckAuthCodeResponse.builder().isVerified(false).build();
    }
}
