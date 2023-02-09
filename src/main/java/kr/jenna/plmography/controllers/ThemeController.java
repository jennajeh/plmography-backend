package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.exceptions.ThemeNotFound;
import kr.jenna.plmography.services.theme.GetThemesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    private GetThemesService getThemesService;

    public ThemeController(GetThemesService getThemesService) {
        this.getThemesService = getThemesService;
    }

    @GetMapping
    public ThemesDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return getThemesService.list(page, size);
    }

    @GetMapping("/{themeId}/contents")
    public ContentsDto themeList(
            @PathVariable Long themeId,
            @RequestParam(required = false) String platform,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size
    ) {
        return getThemesService.themeList(themeId, platform, page, size);
    }

    @GetMapping("/top-rank")
    public ThemesDto top3Hit() {
        return getThemesService.top3Hit();
    }

    @ExceptionHandler(ThemeNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String themeNotFound() {
        return "Theme not found!";
    }
}
