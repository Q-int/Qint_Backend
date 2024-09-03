package org.example.qint_backend.domain.auth.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class PasswordNotValidException extends QintException {

    public static QintException EXCEPTION = new PasswordNotValidException();

    private PasswordNotValidException() {
        super(ErrorCode.PASSWORD_NOT_VALID);
    }
}
