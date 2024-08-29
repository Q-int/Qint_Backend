package org.example.qint_backend.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.question.domain.repository.UserIncorrectAnswersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetJudgmentResult {
    private final UserIncorrectAnswersRepository userIncorrectAnswersRepository;
       
}