package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.login.LoginRequestDto;
import kr.jenna.plmography.dtos.login.LoginResultDto;
import kr.jenna.plmography.exceptions.LoginFailed;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.services.user.LoginService;
import kr.jenna.plmography.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    private LoginService loginService;
    private JwtUtil jwtUtil;

    public SessionController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        User user = loginService.login(
                new Email(loginRequestDto.getEmail()), new Password(loginRequestDto.getPassword()));

        String accessToken = jwtUtil.encode(user.getId());

        return new LoginResultDto(accessToken);
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login failed!";
    }
}
