package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.User.UserProfileRequestDto;
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

        User beforeProfile = User.fake();

        given(userRepository.findById(userId)).willReturn(Optional.of(beforeProfile));

        UserProfileRequestDto userProfileRequestDto = new UserProfileRequestDto("강보니", "new image");

        User afterProfile = patchUserService.update(userId, userProfileRequestDto);

        assertThat(afterProfile.getNickname())
                .isEqualTo(new Nickname(userProfileRequestDto.getNickname()));
        assertThat(afterProfile.getProfileImage())
                .isEqualTo(new ProfileImage(userProfileRequestDto.getProfileImage()));
    }

}
