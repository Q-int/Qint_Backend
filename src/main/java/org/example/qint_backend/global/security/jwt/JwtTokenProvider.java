package org.example.qint_backend.global.security.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.auth.domain.RefreshToken;
import org.example.qint_backend.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.qint_backend.domain.auth.exception.ExpiredTokenException;
import org.example.qint_backend.domain.auth.exception.InvalidTokenException;
import org.example.qint_backend.domain.auth.presentation.dto.response.TokenResponse;
import org.example.qint_backend.global.security.auth.AuthDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";

    private static final String TOKEN_TYPE = "typ";
    private static final String TOKEN_ROLE = "role";

    public TokenResponse generateToken(String email, String role) {
        String accessToken = generateToken(email, role, ACCESS_KEY, jwtProperties.getAccessExpiration());
        String refreshToken = generateToken(email, role, REFRESH_KEY, jwtProperties.getRefreshExpiration());

        refreshTokenRepository.save(RefreshToken.builder()
                .token(refreshToken)
                .ttl(jwtProperties.getRefreshExpiration() * 1000)
                .email(email)
                .build()
        );

        return new TokenResponse(accessToken, refreshToken);
    }

    private String generateToken(String email, String role, String type, Long exp) {
        return Jwts.builder()
                .setSubject(email)
                .setHeaderParam(TOKEN_TYPE, type)
                .claim(TOKEN_ROLE, role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());

        if (isBearerToken(bearer)) {
            return bearer.substring(jwtProperties.getPrefix().length() + 1);
        }

        return null;
    }

    public boolean isBearerToken(String token) {
        return (token != null)
                && (token.startsWith(jwtProperties.getPrefix()))
                && (token.length() > jwtProperties.getPrefix().length() + 1);
    }

    public Authentication getAuthenticationFromToken(String token) {
        Claims body = getJws(token).getBody();
        if (!isNotRefreshToken(token)) throw InvalidTokenException.EXCEPTION;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isNotRefreshToken(String token) {
        return !REFRESH_KEY.equals(getJws(token).getHeader().get(TOKEN_TYPE).toString());
    }

    public String getRole(String token) {
        return getJws(token).getBody().get(TOKEN_ROLE).toString();
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private UserDetails getDetails(Claims body) {
        return authDetailsService.loadUserByUsername(body.getSubject());
    }
}