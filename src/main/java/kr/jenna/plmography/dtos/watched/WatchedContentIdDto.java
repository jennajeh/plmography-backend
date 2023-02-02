package kr.jenna.plmography.dtos.watched;

public class WatchedContentIdDto {
    private Long tmdbId;

    public WatchedContentIdDto() {
    }

    public WatchedContentIdDto(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Long getTmdbId() {
        return tmdbId;
    }
}
