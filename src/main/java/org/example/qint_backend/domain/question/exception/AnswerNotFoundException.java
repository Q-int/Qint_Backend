package org.example.qint_backend.domain.question.exception;

import org.example.qint_backend.global.err.exception.ErrorCode;
import org.example.qint_backend.global.err.exception.QintException;

public class AnswerNotFoundException extends QintException{

    public static final QintException EXCEPTION = new AnswerNotFoundException();

    private AnswerNotFoundException() {
        super(ErrorCode.ANSWER_NOT_FOUND);
    }

}
