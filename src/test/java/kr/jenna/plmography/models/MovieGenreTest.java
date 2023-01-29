package kr.jenna.plmography.models;

import kr.jenna.plmography.models.eunm.MovieGenre;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieGenreTest {

    @Test
    void of() {
        int value = 28;

        MovieGenre movieGenre = MovieGenre.of(value);

        assertThat(movieGenre.getValue()).isEqualTo(value);
        assertThat(movieGenre.getGenreName()).isEqualTo("Action");
    }
}
