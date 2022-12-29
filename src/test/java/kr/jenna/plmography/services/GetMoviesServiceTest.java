package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetMoviesServiceTest {
    private GetMoviesService getMoviesService;
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        getMoviesService = new GetMoviesService(movieRepository);
    }

    @Test
    void list() {
        Movie movie = mock(Movie.class);

        given(movieRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(movie)));

        Integer page = 1;
        Integer size = 8;

        assertThat(getMoviesService.list(page, size)).isNotNull();
        assertThat(getMoviesService.list(page, size)).hasSize(1);
    }
}
