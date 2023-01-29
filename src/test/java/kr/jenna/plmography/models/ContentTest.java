package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.models.vo.WatchedUserId;
import kr.jenna.plmography.models.vo.WishUserId;
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

        assertThat(contentDto).isEqualTo(new ContentDto(1L, "1", "1", "imageUrl", "아바타", "Avatar", 2022,
                3000.0, "netflix", "movie", "판타지 영화"));
    }

    @Test
    void toggleWish() {
        Content content = Content.fake();

        WishUserId wishUserId = new WishUserId(1L);

        content.toggleWish(wishUserId);

        assertThat(content.getWishUserIds()).hasSize(1);

        content.toggleWish(wishUserId);

        assertThat(content.getWishUserIds()).hasSize(0);
    }

    @Test
    void toggleWatched() {
        Content content = Content.fake();

        WatchedUserId watchedUserId = new WatchedUserId(1L);

        content.toggleWatched(watchedUserId);

        assertThat(content.getWatchedUserIds()).hasSize(1);

        content.toggleWatched(watchedUserId);

        assertThat(content.getWatchedUserIds()).hasSize(0);
    }
}
