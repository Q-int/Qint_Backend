package org.example.qint_backend.global.err.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QintException extends RuntimeException {

    private final ErrorCode errorCode;
}