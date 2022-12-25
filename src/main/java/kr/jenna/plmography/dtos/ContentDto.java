package kr.jenna.plmography.dtos;

import java.util.Objects;

public class ContentDto {
    private Long id;
    private String imageUrl;
    private String korTitle;
    private String engTitle;
    private String releaseDate;
    private String genres;
    private String description;

    public ContentDto() {
    }

    public ContentDto(Long id, String imageUrl, String korTitle,
                      String engTitle, String releaseDate,
                      String genres, String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.korTitle = korTitle;
        this.engTitle = engTitle;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "ContentDto{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", korTitle='" + korTitle + '\'' +
                ", engTitle='" + engTitle + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genres='" + genres + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        ContentDto otherContentDto = (ContentDto) other;

        return Objects.equals(id, otherContentDto.id) &&
                Objects.equals(imageUrl, otherContentDto.imageUrl) &&
                Objects.equals(korTitle, otherContentDto.korTitle) &&
                Objects.equals(engTitle, otherContentDto.engTitle) &&
                Objects.equals(releaseDate, otherContentDto.releaseDate) &&
                Objects.equals(genres, otherContentDto.genres) &&
                Objects.equals(description, otherContentDto.description);
    }
}
