package org.example.qint_backend.domain.auth.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.auth.presentation.dto.request.LoginRequest;
import org.example.qint_backend.domain.auth.presentation.dto.request.SignupRequest;
import org.example.qint_backend.domain.auth.presentation.dto.response.TokenResponse;
import org.example.qint_backend.domain.auth.service.LoginService;
import org.example.qint_backend.domain.auth.service.SignupService;
import org.example.qint_backend.domain.auth.service.TokenRefreshService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    private final SignupService signupService;

    private final TokenRefreshService tokenRefreshService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest signupRequest) {
        signupService.execute(signupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return loginService.execute(loginRequest);
    }

    @PutMapping("/auth")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }
}