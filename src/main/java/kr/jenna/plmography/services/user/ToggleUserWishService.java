package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.wish.WishContentIdsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.WishContentId;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleUserWishService {
    private UserRepository userRepository;
    private ContentRepository contentRepository;

    public ToggleUserWishService(UserRepository userRepository,
                                 ContentRepository contentRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    public WishContentIdsDto toggleWish(Long userId, WishContentId wishContentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound());

        Content content = contentRepository.findByTmdbId(wishContentId.getValue())
                .orElseThrow(() -> new ContentNotFound());

        user.toggleWish(wishContentId);

        return new WishContentIdsDto(user.getWishContentIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
