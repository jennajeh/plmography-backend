package kr.jenna.plmography.services.subscribe;

import kr.jenna.plmography.dtos.subscribe.MySubscribeDto;
import kr.jenna.plmography.dtos.subscribe.OtherSubscribeDto;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.SubscribeRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetSubscribesServiceTest {

    @Test
    void mySubscribeList() {
        SubscribeRepository subscribeRepository = mock(SubscribeRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        GetSubscribesService getSubscribesService =
                new GetSubscribesService(subscribeRepository, userRepository);

        MySubscribeDto mySubscribeDto = getSubscribesService.mySubscribeCount(1L);

        assertThat(mySubscribeDto).isNotNull();
    }

    @Test
    void otherSubscribeList() {
        SubscribeRepository subscribeRepository = mock(SubscribeRepository.class);
        UserRepository userRepository = mock(UserRepository.class);

        GetSubscribesService getSubscribesService =
                new GetSubscribesService(subscribeRepository, userRepository);

        given(userRepository.findById(any())).willReturn(Optional.of(User.fake()));

        OtherSubscribeDto otherSubscribeDto =
                getSubscribesService.otherSubscribeCount(1L, 1L);

        assertThat(otherSubscribeDto).isNotNull();
    }
}
