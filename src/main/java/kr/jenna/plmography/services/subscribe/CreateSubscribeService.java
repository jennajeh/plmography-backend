package kr.jenna.plmography.services.subscribe;

import kr.jenna.plmography.models.Subscribe;
import kr.jenna.plmography.models.vo.FollowingId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.SubscribeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateSubscribeService {
    private final SubscribeRepository subscribeRepository;

    public CreateSubscribeService(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    public void follow(Long userId, Long followingId) {
        boolean alreadyFollowed = subscribeRepository.existsByUserIdAndFollowingId(
                new UserId(userId), new FollowingId(followingId));

        if (!alreadyFollowed) {
            Subscribe subscribe = new Subscribe(new UserId(userId), new FollowingId(followingId));

            subscribeRepository.save(subscribe);
        }
    }
}
