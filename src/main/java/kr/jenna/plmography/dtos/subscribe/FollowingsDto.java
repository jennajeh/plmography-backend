package kr.jenna.plmography.dtos.subscribe;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class FollowingsDto {
    private List<FollowingDto> followings;
    private PagesDto pages;

    public FollowingsDto() {
    }

    public FollowingsDto(List<FollowingDto> followings, PagesDto pages) {
        this.followings = followings;
        this.pages = pages;
    }

    public List<FollowingDto> getFollowings() {
        return followings;
    }

    public PagesDto getPages() {
        return pages;
    }
}
