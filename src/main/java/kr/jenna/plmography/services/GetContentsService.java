package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetContentsService {
    private ContentRepository contentRepository;

    public GetContentsService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Page<Content> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        
        return contentRepository.findAll(pageable);
    }
}
