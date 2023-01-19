package kr.jenna.plmography.dtos.Content;

import kr.jenna.plmography.dtos.Page.PagesDto;

import java.util.List;

public class ContentsDto {
    private List<ContentDto> contents;
    private PagesDto pages;

    public ContentsDto(List<ContentDto> contents, PagesDto pages) {
        this.contents = contents;
        this.pages = pages;
    }

    public List<ContentDto> getContents() {
        return contents;
    }

    public PagesDto getPages() {
        return pages;
    }
}
