package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.watched.WatchedContentIdsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleUserWatchedService {
    private UserRepository userRepository;
    private ContentRepository contentRepository;

    public ToggleUserWatchedService(UserRepository userRepository,
                                    ContentRepository contentRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    public WatchedContentIdsDto toggleWatched(Long userId, WatchedContentId watchedContentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound());

        Content content = contentRepository.findByTmdbId(watchedContentId.getValue())
                .orElseThrow(() -> new ContentNotFound());

        user.toggleWatched(watchedContentId);

        return new WatchedContentIdsDto(user.getWatchedContentIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
