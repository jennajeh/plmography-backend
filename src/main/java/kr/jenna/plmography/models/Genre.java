package kr.jenna.plmography.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    private Long id;

    private String tmdbGenreId;

    private String name;

    @Builder
    public Genre(Long id, String tmdbGenreId, String name) {
        this.id = id;
        this.tmdbGenreId = tmdbGenreId;
        this.name = name;
    }
}
