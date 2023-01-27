package kr.jenna.plmography.services.Content;

import kr.jenna.plmography.dtos.Watched.WatchedUserIdsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.VO.ContentId;
import kr.jenna.plmography.models.VO.WatchedUserId;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleContentWatchedService {
    private ContentRepository contentRepository;

    public ToggleContentWatchedService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }


    public WatchedUserIdsDto toggleWatched(ContentId contentId, WatchedUserId watchedUserId) {
        Content content = contentRepository.findById(contentId.getValue())
                .orElseThrow(() -> new ContentNotFound());

        content.toggleWatched(watchedUserId);

        return new WatchedUserIdsDto(content.getWatchedUserIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
