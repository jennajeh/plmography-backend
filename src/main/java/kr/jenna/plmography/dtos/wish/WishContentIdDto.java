package kr.jenna.plmography.dtos.wish;

public class WishContentIdDto {
    private String tmdbId;

    public WishContentIdDto() {
    }

    public WishContentIdDto(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }
}
