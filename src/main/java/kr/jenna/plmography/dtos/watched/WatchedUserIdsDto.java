package kr.jenna.plmography.dtos.watched;

import kr.jenna.plmography.models.vo.WatchedUserId;

import java.util.Set;

public class WatchedUserIdsDto {
    Set<WatchedUserId> watchedUserIds;

    public WatchedUserIdsDto() {
    }

    public WatchedUserIdsDto(Set<WatchedUserId> watchedUserIds) {
        this.watchedUserIds = watchedUserIds;
    }

    public Set<WatchedUserId> getWatchedUserIds() {
        return watchedUserIds;
    }

    public static WatchedUserIdsDto fake() {
        return new WatchedUserIdsDto(Set.of(new WatchedUserId(1L)));
    }
}
