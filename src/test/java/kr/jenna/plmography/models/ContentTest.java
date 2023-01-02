package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.ContentDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {
    @Test
    void creation() {
        Content content = Content.fake();

        assertThat(content.getTmdbId()).isEqualTo("1");
        assertThat(content.getKorTitle()).isEqualTo("아바타");
        assertThat(content.getEngTitle()).isEqualTo("Avatar");
        assertThat(content.getDescription()).isEqualTo("판타지 영화");
    }

    @Test
    void toMovieDto() {
        Content content = Content.fake();

        ContentDto contentDto = content.toContentDto();

        assertThat(contentDto).isEqualTo(new ContentDto(1L, "1", "1", "imageUrl", "아바타", "Avatar", "2022-12-23",
                "3000", "netflix", "movie", "판타지 영화"));
    }
}
