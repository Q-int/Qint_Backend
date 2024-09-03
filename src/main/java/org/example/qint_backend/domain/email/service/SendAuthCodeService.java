package org.example.qint_backend.domain.email.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.email.domain.EmailVerification;
import org.example.qint_backend.domain.email.domain.repository.EmailVerificationRepository;
import org.example.qint_backend.domain.email.facade.EmailVerificationFacade;
import org.example.qint_backend.domain.email.presentation.dto.request.SendAuthCodeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendAuthCodeService {

    private final EmailService emailService;

    private final EmailVerificationRepository emailVerificationRepository;

    private final EmailVerificationFacade emailVerificationFacade;

    @Transactional
    public void execute(SendAuthCodeRequest sendAuthCodeRequest) {
        String email = sendAuthCodeRequest.getEmail();
        String authCode = generateAuthCode();

        if (emailVerificationRepository.existsByEmail(email)) {
            EmailVerification emailVerification = emailVerificationFacade.getEmailVerificationByEmail(email);
            emailVerification.updateAuthCode(authCode);
        } else {
            emailVerificationRepository.save(new EmailVerification(email, authCode));
        }

        emailService.sendEmail(email, authCode);
    }

    private String generateAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
