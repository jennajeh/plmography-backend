package kr.jenna.plmography.dtos.watched;

public class WatchedContentIdDto {
    private String tmdbId;

    public WatchedContentIdDto() {
    }

    public WatchedContentIdDto(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }
}
