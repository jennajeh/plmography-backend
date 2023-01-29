package kr.jenna.plmography.services.subscribe;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.subscribe.MySubscribeDto;
import kr.jenna.plmography.dtos.subscribe.OtherSubscribeDto;
import kr.jenna.plmography.dtos.subscribe.SubscribeDto;
import kr.jenna.plmography.dtos.subscribe.SubscribeUserInfoDto;
import kr.jenna.plmography.dtos.subscribe.SubscribesDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Subscribe;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.FollowingId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.SubscribeRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetSubscribesService {
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;

    public GetSubscribesService(
            SubscribeRepository subscribeRepository,
            UserRepository userRepository) {
        this.subscribeRepository = subscribeRepository;
        this.userRepository = userRepository;
    }

    public MySubscribeDto mySubscribeCount(Long userId) {
        MySubscribeDto mySubscribeDto = new MySubscribeDto(
                userId,
                // 나의 팔로우 숫자 : userId 에 있는 내 id 의 개수를 센다.
                subscribeRepository.countByUserId(new UserId(userId)),
                // 나의 팔로워 숫자 : followingId 에 있는 내 id 의 개수를 센다.
                subscribeRepository.countByFollowingId(new FollowingId(userId))
        );

        return mySubscribeDto;
    }

    public OtherSubscribeDto otherSubscribeCount(Long myId, Long otherUserId) {
        User otherUser = userRepository.findById(otherUserId)
                .orElseThrow(() -> new UserNotFound());

        return new OtherSubscribeDto(
                subscribeRepository.existsByUserIdAndFollowingId(
                        new UserId(myId), new FollowingId(otherUserId)
                ),
                new SubscribeUserInfoDto(
                        otherUser.getId(),
                        otherUser.getNickname().getValue(),
                        otherUser.getProfileImage().getValue()
                ),
                // 다른 유저의 팔로우 숫자 : userId 에 있는 otherUserId 의 개수를 센다.
                subscribeRepository.countByUserId(new UserId(otherUserId)),
                // 다른 유저의 팔로워 숫자 : followingId 에 있는 otherUserId 의 개수를 센다.
                subscribeRepository.countByFollowingId(new FollowingId(otherUserId))
        );
    }

    public SubscribesDto followingList(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Subscribe> subscribes = subscribeRepository.findAllByUserId(new UserId(userId), pageable);

        List<SubscribeDto> subscribeDtos = subscribes.stream()
                .map(subscribe -> {
                    User user = userRepository.findById(subscribe.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(subscribe.getUserId().getValue()));

                    return new SubscribeDto(
                            user.getId(),
                            user.getNickname().getValue(),
                            user.getProfileImage().getValue(),
                            subscribeRepository.existsByUserIdAndFollowingId(
                                    new UserId(subscribe.getUserId().getValue()),
                                    new FollowingId(subscribe.getFollowingId().getValue()))
                    );
                }).collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(subscribes.getTotalPages());

        return new SubscribesDto(subscribeDtos, pagesDto);
    }

    public SubscribesDto followerList(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Subscribe> subscribes = subscribeRepository.findAllByFollowingId(new FollowingId(userId), pageable);

        List<SubscribeDto> subscribeDtos = subscribes.stream()
                .map(subscribe -> {
                    User user = userRepository.findById(subscribe.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(subscribe.getUserId().getValue()));

                    return new SubscribeDto(
                            user.getId(),
                            user.getNickname().getValue(),
                            user.getProfileImage().getValue(),
                            subscribeRepository.existsByUserIdAndFollowingId(
                                    new UserId(subscribe.getUserId().getValue()),
                                    new FollowingId(subscribe.getFollowingId().getValue()))
                    );
                }).collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(subscribes.getTotalPages());

        return new SubscribesDto(subscribeDtos, pagesDto);
    }
}
