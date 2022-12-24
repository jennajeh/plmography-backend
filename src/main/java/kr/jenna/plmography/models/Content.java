package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.ContentDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
public class Content {
    @Id
    @GeneratedValue
    private Long id;

    private String imageUrl;

    private String korTitle;

    private String engTitle;

    private String releaseDate;

    private String genres;

    @Column(length = 4000)
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Content() {
    }

    public Content(Long id, String imageUrl, String korTitle,
                   String engTitle, String releaseDate, String genres,
                   String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Content fake() {
        return new Content(1L, "imageUrl", "아바타", "Avatar", "2022-12-23",
                "판타지", "판타지 영화", LocalDateTime.now(), LocalDateTime.now());
    }

    public Long getId() {
        return id;
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

    public String getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ContentDto toContentDto() {
        return new ContentDto(id, imageUrl, korTitle,
                engTitle, releaseDate, genres, description);
    }
}
