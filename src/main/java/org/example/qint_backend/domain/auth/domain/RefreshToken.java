package org.example.qint_backend.domain.auth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@RedisHash
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @TimeToLive
    private Long ttl;

    private String email;

    @Builder
    public RefreshToken(String token, Long ttl, String email) {
        this.token = token;
        this.ttl = ttl;
        this.email = email;
    }

    public void update(String refreshToken, Long ttl) {
        this.token = refreshToken;
        this.ttl = ttl;
    }
}
