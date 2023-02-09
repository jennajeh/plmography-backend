package kr.jenna.plmography.dtos.like;

import java.util.List;

public class LikesDto {
    private List<LikeDto> likes;

    public LikesDto(List<LikeDto> likes) {
        this.likes = likes;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }
}
