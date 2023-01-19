package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Recomment.RecommentRegistrationDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.RecommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.services.Recomment.CreateRecommentService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateRecommentServiceTest {

    @Test
    void create() {
        UserRepository userRepository = mock(UserRepository.class);
        RecommentRepository recommentRepository = mock(RecommentRepository.class);
        CreateRecommentService createRecommentService =
                new CreateRecommentService(recommentRepository, userRepository);

        User user = User.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        RecommentRegistrationDto recommentRegistrationDto =
                new RecommentRegistrationDto(1L, "reply", 1L, 1L);

        Recomment recomment = createRecommentService.create(1L, recommentRegistrationDto);

        assertThat(recomment).isNotNull();

        verify(recommentRepository).save(recomment);
    }
}
