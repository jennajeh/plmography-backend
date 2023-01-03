package kr.jenna.plmography.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    @Test
    void creation() {
        Review review = new Review(1L, new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"), false);

        assertThat(review.getReviewBody()).isEqualTo(new ReviewBody("영화가 재미있어요"));
        assertThat(review.getIsDeleted()).isEqualTo(false);
    }

    @Test
    void modify() {
        Review review = Review.fake();

        String body = "완전 강추!!";

        review.modify(body);

        assertThat(review.getReviewBody()).isEqualTo(new ReviewBody(body));
    }
}
