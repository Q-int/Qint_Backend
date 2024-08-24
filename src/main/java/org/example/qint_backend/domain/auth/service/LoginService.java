package org.example.qint_backend.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.auth.domain.type.Role;
import org.example.qint_backend.domain.auth.exception.PasswordNotValidException;
import org.example.qint_backend.domain.auth.presentation.dto.request.LoginRequest;
import org.example.qint_backend.domain.auth.presentation.dto.response.TokenResponse;
import org.example.qint_backend.domain.user.domain.User;
import org.example.qint_backend.domain.user.facade.UserFacade;
import org.example.qint_backend.global.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserFacade userFacade;

    public TokenResponse execute(LoginRequest loginRequest) {
        User user = userFacade.getUserByEmail(loginRequest.getEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw PasswordNotValidException.EXCEPTION;
        }

        return jwtTokenProvider.generateToken(user.getEmail(), Role.USER.toString());
    }
}
