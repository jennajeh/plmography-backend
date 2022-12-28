package kr.jenna.plmography.dtos;

import java.util.Objects;

public class ContentDto {
    private Long id;
    private String tmdbContentId;
    private String tmdbGenreId;
    private String imageUrl;
    private String korTitle;
    private String engTitle;
    private String releaseDate;
    private String popularity;
    private String description;

    public ContentDto() {
    }

    public ContentDto(Long id, String tmdbContentId, String tmdbGenreId,
                      String imageUrl, String korTitle, String engTitle,
                      String releaseDate, String popularity, String description) {
        this.id = id;
        this.tmdbContentId = tmdbContentId;
        this.tmdbGenreId = tmdbGenreId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTmdbContentId() {
        return tmdbContentId;
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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        ContentDto otherDto = (ContentDto) other;

        return Objects.equals(id, otherDto.id)
                && Objects.equals(tmdbContentId, otherDto.tmdbContentId)
                && Objects.equals(tmdbGenreId, otherDto.tmdbGenreId)
                && Objects.equals(imageUrl, otherDto.imageUrl)
                && Objects.equals(korTitle, otherDto.korTitle)
                && Objects.equals(engTitle, otherDto.engTitle)
                && Objects.equals(releaseDate, otherDto.releaseDate)
                && Objects.equals(popularity, otherDto.popularity)
                && Objects.equals(description, otherDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tmdbContentId, tmdbGenreId,
                imageUrl, korTitle, engTitle, releaseDate,
                popularity, description);
    }
}
