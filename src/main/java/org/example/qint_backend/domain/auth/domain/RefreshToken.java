package org.example.qint_backend.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
public class RefreshToken {

    @Id
    private final String id;

    @Indexed
    private String token;

    @TimeToLive
    private Long ttl;

    private String email;
}
