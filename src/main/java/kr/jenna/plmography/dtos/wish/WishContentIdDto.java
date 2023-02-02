package kr.jenna.plmography.dtos.wish;

public class WishContentIdDto {
    private Long tmdbId;

    public WishContentIdDto() {
    }

    public WishContentIdDto(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Long getTmdbId() {
        return tmdbId;
    }
}
