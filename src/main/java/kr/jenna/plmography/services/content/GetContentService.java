package kr.jenna.plmography.services.content;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetContentService {
    private ContentRepository contentRepository;

    public GetContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public ContentDto detail(String tmdbId) {
        Content content = contentRepository.findByTmdbId(tmdbId)
                .orElseThrow(() -> new ContentNotFound());

        return content.toContentDto();
    }
}
