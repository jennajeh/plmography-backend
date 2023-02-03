package kr.jenna.plmography.services.theme;

import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.models.Theme;
import kr.jenna.plmography.repositories.ThemeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetThemesServiceTest {
    private ThemeRepository themeRepository;
    private GetThemesService getThemesService;

    @BeforeEach
    void setUp() {
        themeRepository = mock(ThemeRepository.class);
        getThemesService = new GetThemesService(themeRepository);
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
    void top3Hit() {
        Theme theme = Theme.fake();

        given(themeRepository.findTop3ByOrderByHit_ValueDesc()).willReturn(List.of(theme));

        assertThat(getThemesService.top3Hit().getThemes()).isNotNull();
    }
}
