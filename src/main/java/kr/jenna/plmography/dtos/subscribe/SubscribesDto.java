package kr.jenna.plmography.dtos.subscribe;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class SubscribesDto {
    private List<SubscribeDto> subscribes;
    private PagesDto pages;

    public SubscribesDto() {
    }

    public SubscribesDto(List<SubscribeDto> subscribes, PagesDto pages) {
        this.subscribes = subscribes;
        this.pages = pages;
    }

    public List<SubscribeDto> getSubscribes() {
        return subscribes;
    }

    public PagesDto getPages() {
        return pages;
    }
}
