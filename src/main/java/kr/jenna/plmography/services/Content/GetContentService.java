package kr.jenna.plmography.services.Content;

import kr.jenna.plmography.dtos.Content.ContentDto;
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

    public ContentDto detail(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ContentNotFound(id));

        return content.toContentDto();
    }
}