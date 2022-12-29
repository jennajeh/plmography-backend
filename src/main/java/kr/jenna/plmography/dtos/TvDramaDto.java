package kr.jenna.plmography.dtos;

import java.util.Objects;

public class TvDramaDto {
    private Long id;
    private String tmdbTvId;
    private String tmdbGenreId;
    private String imageUrl;
    private String korTitle;
    private String engTitle;
    private String releaseDate;
    private String popularity;
    private String platform;
    private String description;

    public TvDramaDto() {
    }

    public TvDramaDto(Long id, String tmdbTvId,
                      String tmdbGenreId, String imageUrl,
                      String korTitle, String engTitle,
                      String releaseDate, String popularity,
                      String platform, String description) {
        this.id = id;
        this.tmdbTvId = tmdbTvId;
        this.tmdbGenreId = tmdbGenreId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.platform = platform;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTmdbTvId() {
        return tmdbTvId;
    }

    public String getTmdbGenreId() {
        return tmdbGenreId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getKorTitle() {
        return korTitle;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        TvDramaDto otherDto = (TvDramaDto) other;

        return Objects.equals(id, otherDto.id)
                && Objects.equals(tmdbTvId, otherDto.tmdbTvId)
                && Objects.equals(tmdbGenreId, otherDto.tmdbGenreId)
                && Objects.equals(imageUrl, otherDto.imageUrl)
                && Objects.equals(korTitle, otherDto.korTitle)
                && Objects.equals(engTitle, otherDto.engTitle)
                && Objects.equals(releaseDate, otherDto.releaseDate)
                && Objects.equals(popularity, otherDto.popularity)
                && Objects.equals(platform, otherDto.platform)
                && Objects.equals(description, otherDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tmdbTvId, tmdbGenreId,
                imageUrl, korTitle, engTitle, releaseDate,
                popularity, platform, description);
    }
}
