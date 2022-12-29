package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.MovieDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    private String tmdbMovieId;

    private String tmdbGenreId;

    private String imageUrl;

    private String korTitle;

    private String engTitle;

    private String releaseDate;

    private String popularity;

    private String platform;

    @Column(length = 4000)
    private String description;

    private LocalDateTime createdAt;

    @Builder
    public Movie(Long id, String tmdbMovieId, String tmdbGenreId,
                 String imageUrl, String korTitle, String engTitle,
                 String releaseDate, String popularity, String platform, String description,
                 LocalDateTime createdAt) {
        this.id = id;
        this.tmdbMovieId = tmdbMovieId;
        this.tmdbGenreId = tmdbGenreId;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.platform = platform;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Movie fake() {
        return new Movie(1L, "1", "1", "imageUrl", "아바타", "Avatar", "2022-12-23",
                "3000", "", "판타지 영화", LocalDateTime.now());
    }

    public MovieDto toMovieDto() {
        return new MovieDto(id, tmdbMovieId, tmdbGenreId, imageUrl, korTitle,
                engTitle, releaseDate, popularity, platform, description);
    }
}
