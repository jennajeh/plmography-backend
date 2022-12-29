package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.MovieDto;
import kr.jenna.plmography.dtos.MoviesDto;
import kr.jenna.plmography.dtos.PagesDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.services.GetMovieService;
import kr.jenna.plmography.services.GetMoviesService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private GetMovieService getMovieService;
    private GetMoviesService getMoviesService;

    public MovieController(GetMovieService getMovieService, GetMoviesService getMoviesService) {
        this.getMovieService = getMovieService;
        this.getMoviesService = getMoviesService;
    }

    @GetMapping
    public MoviesDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size
    ) {
        Page<Movie> movies = getMoviesService.list(page, size);

        List<MovieDto> movieDtos = movies.stream()
                .map(movie -> movie.toMovieDto())
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(movies.getTotalPages());

        return new MoviesDto(movieDtos, pagesDto);
    }

    @GetMapping("/{id}")
    public MovieDto detail(@PathVariable Long id) {
        return getMovieService.detail(id);
    }

    @ExceptionHandler(ContentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String contentNotFound() {
        return "Content not found!";
    }
}
