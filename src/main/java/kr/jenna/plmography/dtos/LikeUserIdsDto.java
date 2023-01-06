package kr.jenna.plmography.dtos;

import java.util.Set;

public class LikeUserIdsDto {
    Set<LikeUserIdDto> likeUserIds;

    public LikeUserIdsDto() {
    }

    public LikeUserIdsDto(Set<LikeUserIdDto> likeUserIds) {
        this.likeUserIds = likeUserIds;
    }

    public Set<LikeUserIdDto> getLikeUserIds() {
        return likeUserIds;
    }

    public static LikeUserIdsDto fake() {
        return new LikeUserIdsDto(Set.of(new LikeUserIdDto(1L)));
    }
}
