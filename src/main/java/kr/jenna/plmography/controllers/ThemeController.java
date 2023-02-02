package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.services.theme.GetThemesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return getThemesService.list(page, size);
    }
}
