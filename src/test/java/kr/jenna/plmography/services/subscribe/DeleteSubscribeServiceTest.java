package kr.jenna.plmography.services.subscribe;

import kr.jenna.plmography.models.vo.FollowingId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.SubscribeRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteSubscribeServiceTest {

    @Test
    void unFollow() {
        SubscribeRepository subscribeRepository = mock(SubscribeRepository.class);
        DeleteSubscribeService deleteSubscribeService = new DeleteSubscribeService(subscribeRepository);

        deleteSubscribeService.unFollow(1L, 1L);

        verify(subscribeRepository).deleteAllByUserIdAndFollowingId(
                new UserId(1L), new FollowingId(1L)
        );
    }

}
