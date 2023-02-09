package kr.jenna.plmography.services.theme;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.theme.ThemeDto;
import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.exceptions.ThemeNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.Theme;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.ThemeRepository;
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
public class GetThemesService {
    private final ThemeRepository themeRepository;
    private ContentRepository contentRepository;

    public GetThemesService(ThemeRepository themeRepository,
                            ContentRepository contentRepository) {
        this.themeRepository = themeRepository;
        this.contentRepository = contentRepository;
    }

    public ThemesDto list(Integer page, Integer size) {
        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Theme> themes = themeRepository.findAll(pageable);

        List<ThemeDto> themeDtos = themes.stream()
                .map(theme -> theme.toThemeDto())
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(themes.getTotalPages());

        return new ThemesDto(themeDtos, pagesDto);
    }

    public ThemesDto top3Hit() {
        List<Theme> themes = themeRepository.findTop3ByOrderByHitDesc();

        List<ThemeDto> themeDtos = themes.stream()
                .map(theme -> theme.toThemeDto())
                .collect(Collectors.toList());

        return new ThemesDto(themeDtos);
    }

    public ContentsDto themeList(Long themeId, String platform, Integer page, Integer size) {
        Sort sortBy = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sortBy);

        Specification<Content> spec = (root, query, criteriaBuilder) -> null;

        if (themeId != null) {
            Theme theme = themeRepository.findById(themeId)
                    .orElseThrow(() -> new ThemeNotFound());

            theme.updateHit(theme.getHit().getValue());

            spec = spec.and(ContentSpecification.equalThemeId(theme.getId()));
        }

        if (platform != null) {
            spec = spec.and(ContentSpecification.likePlatform(platform));
        }

        Page<Content> contents = contentRepository.findAll(spec, pageable);

        List<ContentDto> contentDtos = contents.stream()
                .map(Content::toContentDto)
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(contents.getTotalPages());

        return new ContentsDto(contentDtos, pagesDto);
    }
}
