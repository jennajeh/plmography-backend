package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.user.UserCountDto;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Nickname;
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
    void profile() {
        Nickname nickname = new Nickname("전제나");

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(User.fake()));

        User user = getUserService.profile(nickname);

        assertThat(user).isNotNull();
    }

    @Test
    void findMe() {
        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        User user = getUserService.findMe(1L);

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
