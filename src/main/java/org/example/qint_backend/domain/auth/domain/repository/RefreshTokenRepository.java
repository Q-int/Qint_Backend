package org.example.qint_backend.domain.auth.domain.repository;

import org.example.qint_backend.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
