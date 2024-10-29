package org.example.qint_backend.domain.question.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class InvalidQuestionIdException extends QintException {

    public static final QintException EXCEPTION = new InvalidQuestionIdException();

    private InvalidQuestionIdException() {
        super(ErrorCode.INVALID_QUESTION_ID_EXCEPTION);
    }
} 