package kr.jenna.plmography.services.theme;

import kr.jenna.plmography.dtos.theme.UpdateHitResponseDto;
import kr.jenna.plmography.models.Theme;
import kr.jenna.plmography.repositories.ThemeRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchThemeServiceTest {

    @Test
    void updateHit() {
        ThemeRepository themeRepository = mock(ThemeRepository.class);
        PatchThemeService patchThemeService = new PatchThemeService(themeRepository);

        Theme theme = Theme.fake();

        given(themeRepository.getReferenceById(any())).willReturn(theme);

        UpdateHitResponseDto updateHitResponseDto =
                patchThemeService.updateHit(theme.getHit().getValue());

        assertThat(theme.getHit().getValue()).isEqualTo(1L);
    }
}
