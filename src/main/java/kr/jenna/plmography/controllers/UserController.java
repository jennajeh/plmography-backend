package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.User.UserCountDto;
import kr.jenna.plmography.dtos.User.UserCreationDto;
import kr.jenna.plmography.dtos.User.UserDto;
import kr.jenna.plmography.dtos.User.UserProfileRequestDto;
import kr.jenna.plmography.dtos.User.UserRegistrationDto;
import kr.jenna.plmography.dtos.User.UsersDto;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.SignupFailed;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.Email;
import kr.jenna.plmography.models.VO.Nickname;
import kr.jenna.plmography.services.User.CreateUserService;
import kr.jenna.plmography.services.User.GetUserService;
import kr.jenna.plmography.services.User.GetUsersService;
import kr.jenna.plmography.services.User.PatchUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private CreateUserService createUserService;
    private GetUserService getUserService;
    private PatchUserService patchUserService;
    private GetUsersService getUsersService;

    public UserController(CreateUserService createUserService,
                          GetUserService getUserService,
                          PatchUserService patchUserService,
                          GetUsersService getUsersService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.patchUserService = patchUserService;
        this.getUsersService = getUsersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto signup(
            @RequestBody UserRegistrationDto userRegistrationDto) {

        User user = createUserService.create(userRegistrationDto);

        return user.toCreateDto();
    }

    @GetMapping
    public UsersDto list() {
        List<UserDto> users = getUsersService.list()
                .stream()
                .map(User::toUserDto)
                .collect(Collectors.toList());

        return new UsersDto(users);
    }

    @GetMapping("/me")
    public UserDto user(@RequestAttribute Long userId) {
        User user = getUserService.findMe(userId);

        return user.toUserDto();
    }

    @GetMapping("/profile")
    public UserDto profile(@RequestParam Nickname nickname) {
        User user = getUserService.profile(nickname);

        return user.toDto();
    }

    @GetMapping("/checkDuplicate")
    public UserCountDto count(@RequestParam boolean countOnly, String email, String nickname) {
        if (countOnly) {
            return getUserService.count(new Email(email), new Nickname(nickname));
        }

        return null;
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDto changeProfile(
            @RequestAttribute Long userId,
            @RequestBody UserProfileRequestDto userProfileRequestDto
    ) {
        User user = patchUserService.update(userId, userProfileRequestDto);

        return user.toDto();
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
