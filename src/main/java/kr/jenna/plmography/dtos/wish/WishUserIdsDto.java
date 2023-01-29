package kr.jenna.plmography.dtos.Wish;

import java.util.Set;

public class WishUserIdsDto {
    Set<WishUserIdDto> wishUserIds;

    public WishUserIdsDto() {
    }

    public WishUserIdsDto(Set<WishUserIdDto> wishUserIds) {
        this.wishUserIds = wishUserIds;
    }

    public Set<WishUserIdDto> getWishUserIds() {
        return wishUserIds;
    }

    public static WishUserIdsDto fake() {
        return new WishUserIdsDto(Set.of(new WishUserIdDto(1L)));
    }
}

