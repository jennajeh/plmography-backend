package kr.jenna.plmography.dtos.content;

import java.util.Objects;

public class ContentDto {
    private Long id;
    private Long tmdbId;
    private String tmdbGenreId;
    private String imageUrl;
    private String korTitle;
    private String engTitle;
    private int releaseDate;
    private double popularity;
    private String platform;
    private String type;
    private Long themeId;
    private Long expiredDateOnNetflix;
    private Integer rottenTomatoScore;
    private Double imdbScore;
    private String description;

    public ContentDto(Long id, Long tmdbId, String tmdbGenreId,
                      String imageUrl, String korTitle, String engTitle,
                      int releaseDate, double popularity, String platform,
                      String type, Long themeId, Long expiredDateOnNetflix,
                      Integer rottenTomatoScore, Double imdbScore, String description) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.tmdbGenreId = tmdbGenreId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.platform = platform;
        this.type = type;
        this.themeId = themeId;
        this.expiredDateOnNetflix = expiredDateOnNetflix;
        this.rottenTomatoScore = rottenTomatoScore;
        this.imdbScore = imdbScore;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getTmdbId() {
        return tmdbId;
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

    public int getReleaseDate() {
        return releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPlatform() {
        return platform;
    }

    public String getType() {
        return type;
    }

    public Long getThemeId() {
        return themeId;
    }

    public Long getExpiredDateOnNetflix() {
        return expiredDateOnNetflix;
    }

    public Integer getRottenTomatoScore() {
        return rottenTomatoScore;
    }

    public Double getImdbScore() {
        return imdbScore;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        ContentDto otherDto = (ContentDto) other;

        return Objects.equals(id, otherDto.id)
                && Objects.equals(tmdbId, otherDto.tmdbId)
                && Objects.equals(tmdbGenreId, otherDto.tmdbGenreId)
                && Objects.equals(imageUrl, otherDto.imageUrl)
                && Objects.equals(korTitle, otherDto.korTitle)
                && Objects.equals(engTitle, otherDto.engTitle)
                && Objects.equals(releaseDate, otherDto.releaseDate)
                && Objects.equals(popularity, otherDto.popularity)
                && Objects.equals(platform, otherDto.platform)
                && Objects.equals(type, otherDto.type)
                && Objects.equals(themeId, otherDto.themeId)
                && Objects.equals(description, otherDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tmdbId, tmdbGenreId,
                imageUrl, korTitle, engTitle, releaseDate,
                popularity, platform, type, themeId, description);
    }
}
