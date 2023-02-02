package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.favorite.FovoriteContentIdsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.FavoriteContentId;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleUserFavoriteService {
    private UserRepository userRepository;
    private ContentRepository contentRepository;

    public ToggleUserFavoriteService(UserRepository userRepository,
                                     ContentRepository contentRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    public FovoriteContentIdsDto toggleFavorite(Long userId, FavoriteContentId favoriteContentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound());

        Content content = contentRepository.findByTmdbId(favoriteContentId.getValue())
                .orElseThrow(() -> new ContentNotFound());

        user.toggleFavorite(favoriteContentId);

        return new FovoriteContentIdsDto(user.getFavoriteContentIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
