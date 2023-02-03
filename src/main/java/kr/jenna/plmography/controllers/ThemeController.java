package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.dtos.theme.UpdateHitResponseDto;
import kr.jenna.plmography.exceptions.ThemeNotFound;
import kr.jenna.plmography.services.theme.GetThemesService;
import kr.jenna.plmography.services.theme.PatchThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    private GetThemesService getThemesService;
    private PatchThemeService patchThemeService;

    public ThemeController(GetThemesService getThemesService,
                           PatchThemeService patchThemeService) {
        this.getThemesService = getThemesService;
        this.patchThemeService = patchThemeService;
    }

    @GetMapping
    public ThemesDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return getThemesService.list(page, size);
    }

    @GetMapping("/{themeId}/contents")
    public ContentsDto themeList(@PathVariable Long themeId) {
        return getThemesService.themeList(themeId);
    }

    @PatchMapping("/{themeId}")
    public UpdateHitResponseDto updateHit(@PathVariable Long themeId) {
        return patchThemeService.updateHit(themeId);
    }

    @GetMapping("/topHit")
    public ThemesDto top3Hit() {
        return getThemesService.top3Hit();
    }

    @ExceptionHandler(ThemeNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String themeNotFound() {
        return "Theme not found!";
    }
}
