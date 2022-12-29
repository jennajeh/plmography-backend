package kr.jenna.plmography.dtos;

import java.util.List;

public class TvDramasDto {
    private List<TvDramaDto> tvDramas;
    private PagesDto pages;

    public TvDramasDto(List<TvDramaDto> tvDramas, PagesDto pages) {
        this.tvDramas = tvDramas;
        this.pages = pages;
    }

    public List<TvDramaDto> getTvDramas() {
        return tvDramas;
    }

    public PagesDto getPages() {
        return pages;
    }
}
