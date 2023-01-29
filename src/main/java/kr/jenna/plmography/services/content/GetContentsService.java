package kr.jenna.plmography.services.Content;

import kr.jenna.plmography.dtos.Content.ContentDto;
import kr.jenna.plmography.dtos.Content.ContentsDto;
import kr.jenna.plmography.dtos.Page.PagesDto;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.specification.ContentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public ContentsDto filter(String platform, String type, String genreId,
                              Integer releaseDate, String searchTitle, String sort, Integer page, Integer size) {
        Sort sortBy = Sort.by("createdAt").descending();

        if (sort.equals("releaseDate")) {
            sortBy = Sort.by("releaseDate").descending();
        }

        if (sort.equals("popularity")) {
            sortBy = Sort.by("popularity").descending();
        }

        if (sort.equals("korTitle")) {
            sortBy = Sort.by("korTitle").ascending();
        }

        Pageable pageable = PageRequest.of(page - 1, size, sortBy);

        Specification<Content> spec = (root, query, criteriaBuilder) -> null;

        if (platform != null) {
            spec = spec.and(ContentSpecification.likePlatform(platform));
        }

        if (type != null) {
            spec = spec.and(ContentSpecification.equalType(type));
        }

        if (genreId != null) {
            spec = spec.and(ContentSpecification.likeTmdbGenreId(genreId));
        }

        if (releaseDate != null) {
            spec = spec.and(ContentSpecification.betweenReleaseDate(releaseDate));
        }

        if (searchTitle != null) {
            spec = spec.and(ContentSpecification.likeKorTitle(searchTitle))
                    .or(ContentSpecification.likeEngTitle(searchTitle));
        }

        Page<Content> contents = contentRepository.findAll(spec, pageable);

        List<ContentDto> contentDtos = contents.stream()
                .map(Content::toContentDto)
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(contents.getTotalPages());

        return new ContentsDto(contentDtos, pagesDto);
    }
}
