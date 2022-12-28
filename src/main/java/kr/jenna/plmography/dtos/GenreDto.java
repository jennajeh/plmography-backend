package kr.jenna.plmography.dtos;

import java.util.Objects;

public class GenreDto {
    private Long id;
    private String tmdbContentId;
    private String tmdbGenreId;
    private String name;

    public GenreDto() {
    }

    public GenreDto(Long id, String tmdbContentId, String tmdbGenreId, String name) {
        this.id = id;
        this.tmdbContentId = tmdbContentId;
        this.tmdbGenreId = tmdbGenreId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getTmdbContentId() {
        return tmdbContentId;
    }

    public String getTmdbGenreId() {
        return tmdbGenreId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        GenreDto otherGenre = (GenreDto) other;
        return Objects.equals(id, otherGenre.id)
                && Objects.equals(tmdbContentId, otherGenre.tmdbContentId)
                && Objects.equals(tmdbGenreId, otherGenre.tmdbGenreId)
                && Objects.equals(name, otherGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tmdbContentId, tmdbGenreId, name);
    }
}
