package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.ContentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Content {
    @Id
    @GeneratedValue
    private Long id;

    private String tmdbContentId;

    private String tmdbGenreId;

    private String imageUrl;

    private String korTitle;

    private String engTitle;

    private String releaseDate;

    private String popularity;

    @Column(length = 4000)
    private String description;

    private LocalDateTime createdAt;

    @Builder
    public Content(Long id, String tmdbContentId, String tmdbGenreId,
                   String imageUrl, String korTitle, String engTitle,
                   String releaseDate, String popularity, String description,
                   LocalDateTime createdAt) {
        this.id = id;
        this.tmdbContentId = tmdbContentId;
        this.tmdbGenreId = tmdbGenreId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Content fake() {
        return new Content(1L, "1", "1", "imageUrl", "아바타", "Avatar", "2022-12-23",
                "3000", "판타지 영화", LocalDateTime.now());
    }

    public ContentDto toContentDto() {
        return new ContentDto(id, tmdbContentId, tmdbGenreId, imageUrl, korTitle,
                engTitle, releaseDate, popularity, description);
    }
}
