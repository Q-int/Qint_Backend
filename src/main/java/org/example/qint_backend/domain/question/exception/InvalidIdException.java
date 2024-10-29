package org.example.qint_backend.domain.question.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class InvalidIdException extends QintException {
    public static final QintException EXCEPTION = new InvalidIdException();

    //Id는 questionId와 answerId를 포함한다.
    private InvalidIdException() {
    }
}
