package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.UserCountDto;
import kr.jenna.plmography.dtos.UserCreationDto;
import kr.jenna.plmography.dtos.UserDto;
import kr.jenna.plmography.dtos.UserRegistrationDto;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.SignupFailed;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.services.CreateUserService;
import kr.jenna.plmography.services.GetUserService;
import kr.jenna.plmography.services.PatchUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private CreateUserService createUserService;
    private GetUserService getUserService;
    private PatchUserService patchUserService;

    public UserController(CreateUserService createUserService, GetUserService getUserService, PatchUserService patchUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.patchUserService = patchUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto signup(
            @RequestBody UserRegistrationDto userRegistrationDto) {

        User user = createUserService.create(userRegistrationDto);

        return user.toCreateDto();
    }

    @GetMapping("/{id}")
    public UserDto detail(@RequestAttribute Long userId) {
        User user = getUserService.detail(userId);

        return user.toUserDto();
    }

    @GetMapping
    public UserCountDto count(@RequestParam boolean countOnly, String email, String nickname) {
        if (countOnly) {
            return getUserService.count(new Email(email), new Nickname(nickname));
        }

        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @RequestBody UserDto userDto,
            @PathVariable Long userId
    ) {
        patchUserService.update(userId, userDto);
    }

    @ExceptionHandler(NicknameAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String nicknameAlreadyExist() {
        return "Nickname already exist!";
    }

    @ExceptionHandler(SignupFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String signUpFailed() {
        return "Sign up failed!";
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }
}
