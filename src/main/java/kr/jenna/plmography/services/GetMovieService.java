package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.MovieDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetMovieService {
    private MovieRepository movieRepository;

    public GetMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDto detail(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ContentNotFound(id));

        return movie.toMovieDto();
    }
}
