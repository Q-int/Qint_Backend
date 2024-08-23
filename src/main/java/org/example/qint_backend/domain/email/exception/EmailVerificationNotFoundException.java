package org.example.qint_backend.domain.email.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class EmailVerificationNotFoundException extends QintException {

    public static QintException EXCEPTION = new EmailVerificationNotFoundException();

    private EmailVerificationNotFoundException() {
        super(ErrorCode.EMAIL_VERIFICATION_NOT_FOUND);
    }
}
