package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.ContentDto;
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


    public ContentDto detail(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ContentNotFound(contentId));

        return content.toContentDto();
    }
}
