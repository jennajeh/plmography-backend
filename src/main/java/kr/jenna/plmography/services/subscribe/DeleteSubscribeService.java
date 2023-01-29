package kr.jenna.plmography.services.subscribe;

import kr.jenna.plmography.models.vo.FollowingId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.SubscribeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteSubscribeService {
    private final SubscribeRepository subscribeRepository;

    public DeleteSubscribeService(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    public void unFollow(Long userId, Long followingId) {
        subscribeRepository.deleteAllByUserIdAndFollowingId(
                new UserId(userId), new FollowingId(followingId)
        );
    }
}
