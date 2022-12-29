package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.MovieDto;
import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetMovieServiceTest {
    private GetMovieService getMovieService;
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        getMovieService = new GetMovieService(movieRepository);
    }

    @Test
    void detail() {
        given(movieRepository.findById(any()))
                .willReturn(Optional.of(Movie.fake()));

        MovieDto movieDto = getMovieService.detail(1L);

        verify(movieRepository).findById(1L);

        assertThat(movieDto.getKorTitle()).isEqualTo("아바타");
        assertThat(movieDto.getEngTitle()).isEqualTo("Avatar");
    }

}
