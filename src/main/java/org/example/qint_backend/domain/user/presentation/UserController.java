package org.example.qint_backend.domain.user.presentation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.user.presentation.dto.response.UserAnswersResponse;
import org.example.qint_backend.domain.user.presentation.dto.response.UserIncorrectAnswerResponse;
import org.example.qint_backend.domain.user.service.GetIncorrectProblemsService;
import org.example.qint_backend.domain.user.service.GetMyPageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final GetMyPageService getMyPageService;

    private final GetIncorrectProblemsService getIncorrectProblemsService;

    @GetMapping("/info")
    public UserAnswersResponse selectUserInfo() {
        return getMyPageService.execute();
    }

    @GetMapping("/incorrect-problems")
    public UserIncorrectAnswerResponse selectIncorrectAnswers() {
        return getIncorrectProblemsService.execute();
    }

}
