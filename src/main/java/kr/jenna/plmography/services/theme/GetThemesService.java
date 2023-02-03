package kr.jenna.plmography.services.theme;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.theme.ThemeDto;
import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.models.Theme;
import kr.jenna.plmography.repositories.ThemeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetThemesService {
    private final ThemeRepository themeRepository;

    public GetThemesService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
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
        List<Theme> themes = themeRepository.findTop3ByOrderByHit_ValueDesc();

        List<ThemeDto> themeDtos = themes.stream()
                .map(theme -> theme.toThemeDto())
                .collect(Collectors.toList());

        return new ThemesDto(themeDtos);
    }
}
