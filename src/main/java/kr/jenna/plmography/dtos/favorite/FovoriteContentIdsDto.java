package kr.jenna.plmography.dtos.favorite;

import java.util.Set;

public class FovoriteContentIdsDto {
    Set<FavoriteContentIdDto> favoriteContentIds;

    public FovoriteContentIdsDto() {
    }

    public FovoriteContentIdsDto(Set<FavoriteContentIdDto> favoriteContentIds) {
        this.favoriteContentIds = favoriteContentIds;
    }

    public Set<FavoriteContentIdDto> getFavoriteContentIds() {
        return favoriteContentIds;
    }

    public static FovoriteContentIdsDto fake() {
        return new FovoriteContentIdsDto(
                Set.of(new FavoriteContentIdDto(1L)));
    }
}
