package kr.jenna.plmography.dtos;

import java.util.List;

public class MoviesDto {
    private List<MovieDto> movies;
    private PagesDto pages;

    public MoviesDto(List<MovieDto> movies, PagesDto pages) {
        this.movies = movies;
        this.pages = pages;
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public PagesDto getPages() {
        return pages;
    }
}
