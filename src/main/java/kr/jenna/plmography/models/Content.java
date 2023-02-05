package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.content.ContentDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tmdbId;

    private String tmdbGenreId;
    
    private Long themeId;

    private String imageUrl;

    private String korTitle;

    private String engTitle;

    private int releaseDate;

    private double popularity;

    private String type;

    private String platform;

    @Column(length = 4000)
    private String description;

    private LocalDateTime createdAt;

    public Content() {
    }

    public Content(Long id, Long tmdbId, String tmdbGenreId,
                   String imageUrl, String korTitle, String engTitle,
                   int releaseDate, double popularity, String type,
                   String platform, String description, Long themeId,
                   LocalDateTime createdAt) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.tmdbGenreId = tmdbGenreId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.type = type;
        this.platform = platform;
        this.description = description;
        this.themeId = themeId;
        this.createdAt = createdAt;
    }

    @Builder
    public Content(Long id, Long tmdbId, String tmdbGenreId,
                   String imageUrl, String korTitle, String engTitle,
                   int releaseDate, double popularity, String platform,
                   String type, String description, LocalDateTime createdAt) {
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
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Content fake() {
        return new Content(1L, 1L, "1", "imageUrl", "아바타", "Avatar", 2022,
                3000, "movie", "netflix", "판타지 영화", 1L, LocalDateTime.now());
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

    public String getType() {
        return type;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDescription() {
        return description;
    }

    public Long getThemeId() {
        return themeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ContentDto toContentDto() {
        return new ContentDto(id, tmdbId, tmdbGenreId, imageUrl, korTitle,
                engTitle, releaseDate, popularity, platform, type, themeId, description);
    }
}
