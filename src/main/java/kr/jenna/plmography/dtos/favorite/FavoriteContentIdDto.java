package kr.jenna.plmography.dtos.favorite;

public class FavoriteContentIdDto {
    private Long tmdbId;

    public FavoriteContentIdDto() {
    }

    public FavoriteContentIdDto(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Long getTmdbId() {
        return tmdbId;
    }
}
