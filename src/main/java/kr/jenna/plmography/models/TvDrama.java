package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.TvDramaDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class TvDrama {
    @Id
    @GeneratedValue
    private Long id;

    private String tmdbTvId;

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
    public TvDrama(Long id, String tmdbTvId, String tmdbGenreId,
                   String imageUrl, String korTitle, String engTitle,
                   String releaseDate, String popularity, String platform, String description,
                   LocalDateTime createdAt) {
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
        this.createdAt = createdAt;
    }

    public static TvDrama fake() {
        return new TvDrama(1L, "1", "1", "imageUrl", "웬즈데이", "Wednesday", "2022-12-23",
                "3000", "넷플릭스", "미스테리", LocalDateTime.now());
    }

    public TvDramaDto toTvDramaDto() {
        return new TvDramaDto(id, tmdbTvId, tmdbGenreId, imageUrl, korTitle,
                engTitle, releaseDate, popularity, platform, description);
    }
}
