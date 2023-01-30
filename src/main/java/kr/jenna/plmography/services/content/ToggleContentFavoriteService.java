package kr.jenna.plmography.services.content;

import kr.jenna.plmography.dtos.favorite.FovoriteUserIdsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.FavoriteUserId;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleContentFavoriteService {
    private ContentRepository contentRepository;

    public ToggleContentFavoriteService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public FovoriteUserIdsDto toggleFavorite(ContentId contentId, FavoriteUserId favoriteUserId) {
        Content content = contentRepository.findById(contentId.getValue())
                .orElseThrow(() -> new ContentNotFound());

        content.toggleFavorite(favoriteUserId);

        return new FovoriteUserIdsDto(content.getFavoriteUserIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
