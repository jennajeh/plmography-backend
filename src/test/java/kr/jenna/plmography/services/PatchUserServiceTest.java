package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.User.UserDto;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.Nickname;
import kr.jenna.plmography.models.VO.ProfileImage;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.services.User.PatchUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchUserServiceTest {
    private UserRepository userRepository;
    private PatchUserService patchUserService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        patchUserService = new PatchUserService(userRepository);
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
        assertThat(user.getProfileImage())
                .isEqualTo(new ProfileImage(userDto.getProfileImage()));
    }

}
