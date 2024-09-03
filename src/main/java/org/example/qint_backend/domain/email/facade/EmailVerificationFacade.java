package org.example.qint_backend.domain.email.facade;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.email.domain.EmailVerification;
import org.example.qint_backend.domain.email.domain.repository.EmailVerificationRepository;
import org.example.qint_backend.domain.email.exception.EmailNotVerifiedException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailVerificationFacade {

    private final EmailVerificationRepository emailVerificationRepository;

    public EmailVerification getEmailVerificationByEmail(String email) {
        return emailVerificationRepository.findByEmail(email).orElseThrow(() -> EmailNotVerifiedException.EXCEPTION);
    }
}
