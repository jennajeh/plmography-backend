package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetMoviesService {
    private MovieRepository movieRepository;

    public GetMoviesService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return movieRepository.findAll(pageable);
    }
}
