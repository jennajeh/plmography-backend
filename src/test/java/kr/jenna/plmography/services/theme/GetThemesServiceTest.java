package kr.jenna.plmography.services.theme;

import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.Theme;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.ThemeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetThemesServiceTest {
    private ThemeRepository themeRepository;
    private ContentRepository contentRepository;
    private GetThemesService getThemesService;

    @BeforeEach
    void setUp() {
        themeRepository = mock(ThemeRepository.class);
        contentRepository = mock(ContentRepository.class);
        getThemesService = new GetThemesService(themeRepository, contentRepository);
    }

    @Test
    void list() {
        Theme theme = Theme.fake();

        given(themeRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(theme)));

        Integer page = 1;
        Integer size = 5;

        ThemesDto themesDto = getThemesService.list(page, size);

        assertThat(themesDto).isNotNull();
        assertThat(themesDto.getThemes().get(0).getTitle()).isEqualTo("혼자 보기 좋은 영화 모음");
    }

    @Test
    void themeList() {
        Page<Content> pages = new PageImpl<>(List.of(Content.fake()));

        given(themeRepository.findById(any())).willReturn(Optional.of(Theme.fake()));

        given(contentRepository.findAll(any(Specification.class), any(Pageable.class)))
                .willReturn(pages);

        Long themeId = 1L;
        String platform = "netflix";
        Integer page = 1;
        Integer size = 8;

        ContentsDto contentsDto = getThemesService.themeList(themeId, platform, page, size);

        assertThat(contentsDto.getContents()).isNotNull();
        assertThat(contentsDto.getContents().get(0).getThemeId()).isEqualTo(themeId);
    }

    @Test
    void top3Hit() {
        Theme theme = Theme.fake();

        given(themeRepository.findTop3ByOrderByHitDesc()).willReturn(List.of(theme));

        assertThat(getThemesService.top3Hit().getThemes()).isNotNull();
    }
}
