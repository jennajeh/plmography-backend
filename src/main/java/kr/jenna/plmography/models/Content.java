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

    private String movie_id;

    private String genre_id;

    private String imageUrl;

    private String korTitle;

    private String engTitle;

    private String releaseDate;

    private String popularity;

    private String trailerUrl;

    @Column(length = 4000)
    private String description;

    private LocalDateTime createdAt;

    public Content() {
    }

    public Content(Long id, String movie_id, String genre_id,
                   String imageUrl, String korTitle, String engTitle,
                   String releaseDate, String popularity,
                   String trailerUrl, String description,
                   LocalDateTime createdAt) {
        this.id = id;
        this.movie_id = movie_id;
        this.genre_id = genre_id;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.trailerUrl = trailerUrl;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String getGenre_id() {
        return genre_id;
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

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Content fake() {
        return new Content(1L, "1", "1", "imageUrl", "아바타", "Avatar", "2022-12-23",
                "3000", "trailerUrl", "판타지 영화", LocalDateTime.now());
    }

    public ContentDto toContentDto() {
        return new ContentDto(id, movie_id, genre_id, imageUrl, korTitle,
                engTitle, releaseDate, popularity, trailerUrl, description);
    }
}
