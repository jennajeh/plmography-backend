package kr.jenna.plmography.controllers;

import jakarta.validation.Valid;
import kr.jenna.plmography.dtos.user.UserCountDto;
import kr.jenna.plmography.dtos.user.UserCreationDto;
import kr.jenna.plmography.dtos.user.UserDto;
import kr.jenna.plmography.dtos.user.UserProfileRequestDto;
import kr.jenna.plmography.dtos.user.UserRegistrationDto;
import kr.jenna.plmography.dtos.user.UsersDto;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.services.user.CreateUserService;
import kr.jenna.plmography.services.user.GetUserService;
import kr.jenna.plmography.services.user.GetUsersService;
import kr.jenna.plmography.services.user.PatchUserService;
import kr.jenna.plmography.utils.S3Uploader;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private CreateUserService createUserService;
    private GetUserService getUserService;
    private PatchUserService patchUserService;
    private GetUsersService getUsersService;
    private S3Uploader s3Uploader;

    public UserController(CreateUserService createUserService,
                          GetUserService getUserService,
                          PatchUserService patchUserService,
                          GetUsersService getUsersService,
                          S3Uploader s3Uploader) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.patchUserService = patchUserService;
        this.getUsersService = getUsersService;
        this.s3Uploader = s3Uploader;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto signup(
            @Valid @RequestBody UserRegistrationDto userRegistrationDto) {

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

        return user.toMyDto();
    }

    @GetMapping("/profile")
    public UserDto profile(@RequestParam Nickname nickname) {
        User user = getUserService.profile(nickname);

        return user.toUserDto();
    }

    @GetMapping("/checkDuplicate")
    public UserCountDto count(
            @RequestParam boolean countOnly,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname) {

        if (countOnly) {
            return getUserService.count(new Email(email), new Nickname(nickname));
        }

        return null;
    }

    @PatchMapping
    public UserDto changeProfile(
            @RequestAttribute Long userId,
            @RequestBody UserProfileRequestDto userProfileRequestDto
    ) {
        User user = patchUserService.update(userId, userProfileRequestDto);

        return user.toChangeUserProfileDto();
    }

    @PostMapping("/upload-image")
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "profileImage");
    }

    @ExceptionHandler(NicknameAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String nicknameAlreadyExist() {
        return "Nickname already exist!";
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }
}
