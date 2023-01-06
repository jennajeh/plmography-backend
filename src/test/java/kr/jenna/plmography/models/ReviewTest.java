package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.ReviewCreationDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    @Test
    void creation() {
        Review review = new Review(1L, new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"));

        assertThat(review.getReviewBody()).isEqualTo(new ReviewBody("영화가 재미있어요"));
    }

    @Test
    void modify() {
        Review review = Review.fake();

        String body = "완전 강추!!";

        review.update(new ReviewBody(body));

        assertThat(review.getReviewBody()).isEqualTo(new ReviewBody(body));
    }

    @Test
    void delete() {
        Review review = Review.fake();

        review.delete();

        assertThat(review.getDeleted()).isTrue();
    }

    @Test
    void toggleLike() {
        Review review = Review.fake();
        LikeUserId likeUserId = new LikeUserId(1L);

        review.toggleLike(likeUserId);

        assertThat(review.getLikeUserIds()).hasSize(1);

        review.toggleLike(likeUserId);

        assertThat(review.getLikeUserIds()).hasSize(0);
    }

    @Test
    void toCreateDto() {
        Review review = Review.fake();

        ReviewCreationDto reviewCreationDto = review.toCreateDto();

        assertThat(reviewCreationDto).isNotNull();
        assertThat(review.getId()).isEqualTo(reviewCreationDto.getId());
    }
}
