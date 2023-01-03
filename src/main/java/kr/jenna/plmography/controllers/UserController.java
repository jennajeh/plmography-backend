package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.UserCreationDto;
import kr.jenna.plmography.dtos.UserDto;
import kr.jenna.plmography.dtos.UserRegistrationDto;
import kr.jenna.plmography.exceptions.SignupFailed;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.services.CreateUserService;
import kr.jenna.plmography.services.GetUserService;
import kr.jenna.plmography.services.UpdateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private CreateUserService createUserService;
    private GetUserService getUserService;
    private UpdateUserService updateUserService;

    public UserController(CreateUserService createUserService, GetUserService getUserService, UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @RequestBody UserDto userDto,
            @PathVariable Long userId
    ) {
        updateUserService.update(userId, userDto);
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
