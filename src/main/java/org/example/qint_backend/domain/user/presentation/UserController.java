package org.example.qint_backend.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.example.qint_backend.domain.user.presentation.dto.response.UserAnswersResponse;
import org.example.qint_backend.domain.user.presentation.dto.response.UserIncorrectAnswerResponse;
import org.example.qint_backend.domain.user.service.GetIncorrectProblemsService;
import org.example.qint_backend.domain.user.service.GetMyPageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final GetMyPageService myPageService;

    private final GetIncorrectProblemsService incorrectProblemsService;

    @GetMapping("/info")
    public UserAnswersResponse selectUserInfo() {
        return myPageService.execute();
    }

    @GetMapping("/incorrect-problems")
    public List<UserIncorrectAnswerResponse> selectIncorrectAnswers() {
        return incorrectProblemsService.execute();
    }

}
