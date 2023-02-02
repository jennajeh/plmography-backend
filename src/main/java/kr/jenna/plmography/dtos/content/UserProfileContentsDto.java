package kr.jenna.plmography.dtos.content;

import java.util.List;

public class UserProfileContentsDto {
    private List<UserProfileContentDto> userProfileContents;

    public UserProfileContentsDto(List<UserProfileContentDto> userProfileContents) {
        this.userProfileContents = userProfileContents;
    }

    public List<UserProfileContentDto> getUserProfileContents() {
        return userProfileContents;
    }
}
