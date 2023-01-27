package kr.jenna.plmography.services.Content;

import kr.jenna.plmography.dtos.Wish.WishUserIdsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.VO.ContentId;
import kr.jenna.plmography.models.VO.WishUserId;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleContentWishService {
    private ContentRepository contentRepository;

    public ToggleContentWishService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public WishUserIdsDto toggleWish(ContentId contentId, WishUserId wishUserId) {
        Content content = contentRepository.findById(contentId.getValue())
                .orElseThrow(() -> new ContentNotFound());

        content.toggleWish(wishUserId);

        return new WishUserIdsDto(content.getWishUserIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
