package kr.jenna.plmography.dtos.favorite;

import kr.jenna.plmography.models.vo.FavoriteUserId;

import java.util.Set;

public class FovoriteUserIdsDto {
    private Set<FavoriteUserId> favoriteUserIds;

    public FovoriteUserIdsDto() {
    }

    public FovoriteUserIdsDto(Set<FavoriteUserId> favoriteUserIds) {
        this.favoriteUserIds = favoriteUserIds;
    }

    public Set<FavoriteUserId> getFavoriteUserIds() {
        return favoriteUserIds;
    }

    public static FovoriteUserIdsDto fake() {
        return new FovoriteUserIdsDto(Set.of(new FavoriteUserId(1L)));
    }
}
