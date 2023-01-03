package kr.jenna.plmography.services;

import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {

    @Test
    void detail() {
        UserRepository userRepository = mock(UserRepository.class);
        GetUserService getUserService = new GetUserService(userRepository);

        given(userRepository.findById(1L))
                .willReturn(Optional.of(User.fake()));

        User user = getUserService.detail(1L);

        assertThat(user).isNotNull();
    }
}
