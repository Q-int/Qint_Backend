package org.example.qint_backend.domain.auth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.qint_backend.domain.user.domain.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public RefreshToken(String token, Long ttl, User user) {
        this.token = token;
        this.ttl = ttl;
        this.user = user;
    }

    public void update(String refreshToken, Long ttl) {
        this.token = refreshToken;
        this.ttl = ttl;
    }
}
