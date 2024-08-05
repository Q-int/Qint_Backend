package org.example.qint_backend.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.qint_backend.global.err.GlobalExceptionFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity http) {
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);

        http.addFilterBefore(globalExceptionFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
