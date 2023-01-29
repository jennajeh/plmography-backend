package kr.jenna.plmography.services.user;

import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUsersServiceTest {

    @Test
    void list() {
        UserRepository userRepository = mock(UserRepository.class);
        GetUsersService getUsersService = new GetUsersService(userRepository);

        given(userRepository.findAll()).willReturn(User.fakes(5));

        assertThat(getUsersService.list()).hasSize(5);
    }
}
