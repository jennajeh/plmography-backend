package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.UserDto;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.Password;
import kr.jenna.plmography.models.ProfileImage;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchUserServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private PatchUserService patchUserService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        patchUserService = new PatchUserService(userRepository, passwordEncoder);
    }

    @Test
    void update() {
        Long userId = 1L;

        User user = User.fake();

        given(userRepository.findById(userId))
                .willReturn(Optional.of(user));

        UserDto userDto = new UserDto(1L,
                "jenna@gmail.com", "강보니", "Asdf123!",
                "여성", 1994, "new image");

        patchUserService.update(userId, userDto);

        assertThat(user.getNickname())
                .isEqualTo(new Nickname(userDto.getNickname()));
        assertThat(user.getPassword())
                .isEqualTo(new Password(userDto.getPassword()));
        assertThat(user.getProfileImage())
                .isEqualTo(new ProfileImage(userDto.getProfileImage()));
    }

}
