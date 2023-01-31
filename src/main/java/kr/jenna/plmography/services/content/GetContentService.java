package kr.jenna.plmography.services.content;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetContentService {
    private UserRepository userRepository;
    private ContentRepository contentRepository;

    public GetContentService(UserRepository userRepository,
                             ContentRepository contentRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    public ContentDto detail(Long tmdbId) {
        Content content = contentRepository.findByTmdbId(tmdbId)
                .orElseThrow(() -> new ContentNotFound());

        return content.toContentDto();
    }

//    public UserProfileContentsDto userProfile(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFound(userId));
//
//        List<Content> contents = contentRepository.findAllByTmdbId(
//                user.getFavoriteContentIds().stream().collect(Collectors.toSet()));
//        return null;
//    }
}
