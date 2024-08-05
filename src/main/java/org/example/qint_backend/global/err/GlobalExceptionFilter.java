package org.example.qint_backend.global.err;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (QintException e) {
            ErrorCode errorCode = e.getErrorCode();
            writeErrorResponse(response, ErrorResponse.of(errorCode.getMessage(), errorCode.getStatusCode()));
        } catch (Exception e) {
            writeErrorResponse(response, ErrorResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
