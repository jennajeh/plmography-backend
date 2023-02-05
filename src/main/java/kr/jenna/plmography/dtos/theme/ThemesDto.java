package kr.jenna.plmography.dtos.theme;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class ThemesDto {
    private List<ThemeDto> themes;
    private PagesDto pages;

    public ThemesDto() {
    }

    public ThemesDto(List<ThemeDto> themes) {
        this.themes = themes;
    }

    public ThemesDto(List<ThemeDto> themes, PagesDto pages) {
        this.themes = themes;
        this.pages = pages;
    }

    public List<ThemeDto> getThemes() {
        return themes;
    }

    public PagesDto getPages() {
        return pages;
    }
}
