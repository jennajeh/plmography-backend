package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.UserCountDto;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    private GetUserService getUserService;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUserService = new GetUserService(userRepository);
    }

    @Test
    void detail() {
        given(userRepository.findById(1L))
                .willReturn(Optional.of(User.fake()));

        User user = getUserService.detail(1L);

        assertThat(user).isNotNull();
    }

    @Test
    void countWithExistingEmailAndNickname() {
        given(userRepository.findAllByEmail(new Email("test@example.com")))
                .willReturn(List.of(User.fake()));
        given(userRepository.findAllByNickname(new Nickname("전제나")))
                .willReturn(List.of(User.fake()));

        UserCountDto userCountDto = getUserService.count(
                new Email("test@example.com"), new Nickname("전제나"));

        assertThat(userCountDto.getCountEmail()).isEqualTo(1);
        assertThat(userCountDto.getCountNickname()).isEqualTo(1);
    }
}
