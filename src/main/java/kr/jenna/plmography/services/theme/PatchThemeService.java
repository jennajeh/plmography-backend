package kr.jenna.plmography.services.theme;

import kr.jenna.plmography.dtos.theme.UpdateHitResponseDto;
import kr.jenna.plmography.exceptions.ThemeNotFound;
import kr.jenna.plmography.models.Theme;
import kr.jenna.plmography.repositories.ThemeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchThemeService {
    private final ThemeRepository themeRepository;

    public PatchThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public UpdateHitResponseDto updateHit(Long themeId) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new ThemeNotFound());

        theme.updateHit(theme.getHit().getValue());

        return new UpdateHitResponseDto(theme.getId());
    }
}
