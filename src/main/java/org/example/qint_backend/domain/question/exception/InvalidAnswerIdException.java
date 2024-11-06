package org.example.qint_backend.domain.question.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class InvalidAnswerIdException extends QintException {
    public static final QintException EXCEPTION = new InvalidAnswerIdException();

    private InvalidAnswerIdException() {
        super(ErrorCode.INVALID_ANSWER_ID_EXCEPTION);
    }
}
