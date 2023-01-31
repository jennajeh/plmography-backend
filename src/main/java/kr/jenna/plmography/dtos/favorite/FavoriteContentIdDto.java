package kr.jenna.plmography.dtos.favorite;

public class FavoriteContentIdDto {
    private String tmdbId;

    public FavoriteContentIdDto() {
    }

    public FavoriteContentIdDto(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }
}
