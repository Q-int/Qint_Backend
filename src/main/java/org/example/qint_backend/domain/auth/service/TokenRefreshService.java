package org.example.qint_backend.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.auth.presentation.dto.response.TokenResponse;
import org.example.qint_backend.global.security.jwt.TokenRefreshUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final TokenRefreshUtil tokenRefreshUtil;

    public TokenResponse execute(String refreshToken) {
        return tokenRefreshUtil.tokenRefresh(refreshToken);
    }
}
