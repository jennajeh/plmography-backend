package kr.jenna.plmography.dtos.watched;

import java.util.Set;

public class WatchedContentIdsDto {
    Set<WatchedContentIdDto> watchedContentIds;

    public WatchedContentIdsDto() {
    }

    public WatchedContentIdsDto(Set<WatchedContentIdDto> watchedContentIds) {
        this.watchedContentIds = watchedContentIds;
    }

    public Set<WatchedContentIdDto> getWatchedContentIds() {
        return watchedContentIds;
    }

    public static WatchedContentIdsDto fake() {
        return new WatchedContentIdsDto(Set.of(new WatchedContentIdDto(1L)));
    }
}
