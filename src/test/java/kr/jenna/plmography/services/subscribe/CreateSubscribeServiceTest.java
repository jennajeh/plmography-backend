package kr.jenna.plmography.services.subscribe;

import kr.jenna.plmography.models.Subscribe;
import kr.jenna.plmography.repositories.SubscribeRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateSubscribeServiceTest {

    @Test
    void follow() {
        SubscribeRepository subscribeRepository = mock(SubscribeRepository.class);
        CreateSubscribeService createSubscribeService
                = new CreateSubscribeService(subscribeRepository);

        Subscribe subscribe = Subscribe.fake();

        given(subscribeRepository.existsByUserIdAndFollowingId(any(), any()))
                .willReturn(false);

        createSubscribeService.follow(
                subscribe.getUserId().getValue(),
                subscribe.getFollowingId().getValue());

        verify(subscribeRepository).save(any(Subscribe.class));
    }

}
