package org.example.qint_backend.domain.email.domain.repository;

import org.example.qint_backend.domain.email.domain.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
}
