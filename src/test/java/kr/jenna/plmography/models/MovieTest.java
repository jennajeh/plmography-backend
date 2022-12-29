package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.MovieDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTest {
    @Test
    void creation() {
        Movie movie = Movie.fake();

        assertThat(movie.getTmdbMovieId()).isEqualTo("1");
        assertThat(movie.getKorTitle()).isEqualTo("아바타");
        assertThat(movie.getEngTitle()).isEqualTo("Avatar");
        assertThat(movie.getDescription()).isEqualTo("판타지 영화");
    }

    @Test
    void toMovieDto() {
        Movie movie = Movie.fake();

        MovieDto movieDto = movie.toMovieDto();

        assertThat(movieDto).isEqualTo(new MovieDto(1L, "1", "1", "imageUrl", "아바타", "Avatar", "2022-12-23",
                "3000", platform, "판타지 영화"));
    }
}
