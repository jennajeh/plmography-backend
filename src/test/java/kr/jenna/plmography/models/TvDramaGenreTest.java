package kr.jenna.plmography.models;

import kr.jenna.plmography.models.eunm.TvDramaGenre;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TvDramaGenreTest {

    @Test
    void of() {
        int value = 10759;

        TvDramaGenre tvDramaGenre = TvDramaGenre.of(value);

        assertThat(tvDramaGenre.getValue()).isEqualTo(value);
        assertThat(tvDramaGenre.getGenreName()).isEqualTo("Action");
    }
}
