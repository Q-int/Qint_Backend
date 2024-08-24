package org.example.qint_backend.global.err.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND(404, "user not found"),

    USER_ALREADY_EXISTS(409, "user already exists"),

    BAD_REQUEST(400, "bad request"),

    EMAIL_NOT_VERIFIED(400, "email not verified"),

    PASSWORD_NOT_VALID(401, "password not valid"),

    INTERNAL_SERVER_ERROR(500, "server error"),

    INVALID_TOKEN_EXCEPTION(401, "invalid token exception"),

    EXPIRED_TOKEN_EXCEPTION(401,"expired token exception");

    private final int statusCode;
    private final String message;
}