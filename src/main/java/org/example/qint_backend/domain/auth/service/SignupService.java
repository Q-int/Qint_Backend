package org.example.qint_backend.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.auth.presentation.dto.request.SignupRequest;
import org.example.qint_backend.domain.email.exception.EmailNotVerifiedException;
import org.example.qint_backend.domain.email.facade.EmailVerificationFacade;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.domain.repository.UserRepository;
import org.example.qint_backend.domain.user.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignupService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final EmailVerificationFacade emailVerificationFacade;

    public void execute(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        String password = passwordEncoder.encode(signupRequest.getPassword());

        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        if (!emailVerificationFacade.getEmailVerificationByEmail(email).getIsVerified()) {
            throw EmailNotVerifiedException.EXCEPTION;
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .correctAnswers(0L)
                .incorrectAnswers(0L)
                .build();

        userRepository.save(user);
    }
}
