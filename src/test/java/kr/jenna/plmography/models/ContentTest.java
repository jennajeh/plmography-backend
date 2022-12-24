package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.ContentDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {
    @Test
    void creation() {
        Content content = Content.fake();

        assertThat(content.getId()).isEqualTo(1L);
        assertThat(content.getKorTitle()).isEqualTo("아바타");
        assertThat(content.getEngTitle()).isEqualTo("Avatar");
        assertThat(content.getGenres()).isEqualTo("판타지");
        assertThat(content.getDescription()).isEqualTo("판타지 영화");
    }

    @Test
    void toContentDto() {
        Content content = Content.fake();

        ContentDto contentDto = content.toContentDto();

        assertThat(contentDto).isEqualTo(new ContentDto(1L, "imageUrl", "아바타",
                "Avatar", "2022-12-23", "판타지", "판타지 영화"));
    }
}
