package org.example.qint_backend.global.security.jwt;

import lombok.RequiredArgsConstructor;

import org.example.qint_backend.domain.auth.domain.RefreshToken;
import org.example.qint_backend.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.qint_backend.domain.auth.exception.InvalidTokenException;
import org.example.qint_backend.domain.auth.presentation.dto.response.TokenResponse;
import org.example.qint_backend.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;

@RequiredArgsConstructor
@Component
public class TokenRefreshUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProperties jwtProperties;

    public TokenResponse tokenRefresh(String token) {
        if (jwtTokenProvider.isNotRefreshToken(token)) {
            throw InvalidTokenException.EXCEPTION;
        }

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> InvalidTokenException.EXCEPTION);

        User user = refreshToken.getUser();
        String role = jwtTokenProvider.getRole(refreshToken.getToken());

        return jwtTokenProvider.generateToken(user, role);
    }
}