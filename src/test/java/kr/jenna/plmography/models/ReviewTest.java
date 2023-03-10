package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.review.ReviewCreationDto;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.LikeUserId;
import kr.jenna.plmography.models.vo.ReviewBody;
import kr.jenna.plmography.models.vo.UserId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    @Test
    void creation() {
        Review review = new Review(new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"));

        assertThat(review.getReviewBody()).isEqualTo(new ReviewBody("영화가 재미있어요"));
    }

    @Test
    void modify() {
        Review review = Review.fake();

        review.modify(5L, new ReviewBody("하하"));

        assertThat(review.getReviewBody().getValue())
                .isEqualTo("하하");
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

    @Test
    void isWriter() {
        Review review = Review.fake();

        assertThat(review.isWriter(1L)).isTrue();
        assertThat(review.isWriter(2L)).isFalse();
    }
}
