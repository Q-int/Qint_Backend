package org.example.qint_backend.domain.email.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class EmailNotVerifiedException extends QintException {

    public static QintException EXCEPTION = new EmailNotVerifiedException();

    private EmailNotVerifiedException() {
        super(ErrorCode.EMAIL_NOT_VERIFIED);
    }
}
