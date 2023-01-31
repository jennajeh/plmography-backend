package kr.jenna.plmography.dtos.subscribe;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class FollowersDto {
    private List<FollowerDto> followers;
    private PagesDto pages;

    public FollowersDto() {
    }

    public FollowersDto(List<FollowerDto> followers, PagesDto pages) {
        this.followers = followers;
        this.pages = pages;
    }

    public List<FollowerDto> getFollowers() {
        return followers;
    }

    public PagesDto getPages() {
        return pages;
    }
}
