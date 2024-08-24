package org.example.qint_backend.domain.email.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.email.presentation.dto.request.EmailVerifyRequest;
import org.example.qint_backend.domain.email.presentation.dto.response.EmailVerifyResponse;
import org.example.qint_backend.domain.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckUsedEmailService {

    private final UserRepository userRepository;

    public EmailVerifyResponse execute(EmailVerifyRequest emailVerifyRequest) {
        boolean exists = userRepository.existsByEmail(emailVerifyRequest.getEmail());

        return EmailVerifyResponse.builder().success(!exists).build();
    }
}
