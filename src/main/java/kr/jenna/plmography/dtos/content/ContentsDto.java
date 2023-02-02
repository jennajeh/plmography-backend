package kr.jenna.plmography.dtos.content;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class ContentsDto {
    private List<ContentDto> contents;
    private PagesDto pages;

    public ContentsDto(List<ContentDto> contents, PagesDto pages) {
        this.contents = contents;
        this.pages = pages;
    }

    public ContentsDto(List<ContentDto> contents) {
        this.contents = contents;
    }

    public List<ContentDto> getContents() {
        return contents;
    }

    public PagesDto getPages() {
        return pages;
    }
}
