package kr.jenna.plmography.dtos;

import java.util.Objects;

public class ContentDto {
    private Long id;
    private String movie_id;
    private String genre_id;
    private String imageUrl;
    private String korTitle;
    private String engTitle;
    private String releaseDate;
    private String popularity;
    private String trailerUrl;
    private String description;

    public ContentDto() {
    }

    public ContentDto(Long id, String movie_id, String genre_id,
                      String imageUrl, String korTitle, String engTitle,
                      String releaseDate, String popularity,
                      String trailerUrl, String description) {
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

    @Override
    public boolean equals(Object other) {
        ContentDto otherDto = (ContentDto) other;

        return Objects.equals(id, otherDto.id)
                && Objects.equals(movie_id, otherDto.movie_id)
                && Objects.equals(genre_id, otherDto.genre_id)
                && Objects.equals(imageUrl, otherDto.imageUrl)
                && Objects.equals(korTitle, otherDto.korTitle)
                && Objects.equals(engTitle, otherDto.engTitle)
                && Objects.equals(releaseDate, otherDto.releaseDate)
                && Objects.equals(popularity, otherDto.popularity)
                && Objects.equals(trailerUrl, otherDto.trailerUrl)
                && Objects.equals(description, otherDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie_id, genre_id,
                imageUrl, korTitle, engTitle, releaseDate,
                popularity, trailerUrl, description);
    }
}
