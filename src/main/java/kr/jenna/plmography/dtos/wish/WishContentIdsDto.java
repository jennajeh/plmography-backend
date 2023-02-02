package kr.jenna.plmography.dtos.wish;

import java.util.Set;

public class WishContentIdsDto {
    Set<WishContentIdDto> wishContentIds;

    public WishContentIdsDto() {
    }

    public WishContentIdsDto(Set<WishContentIdDto> wishContentIds) {
        this.wishContentIds = wishContentIds;
    }

    public Set<WishContentIdDto> getWishContentIds() {
        return wishContentIds;
    }

    public static WishContentIdsDto fake() {
        return new WishContentIdsDto(Set.of(new WishContentIdDto(1L)));
    }
}

