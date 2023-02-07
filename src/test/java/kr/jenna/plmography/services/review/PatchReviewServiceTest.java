package kr.jenna.plmography.services.review;

import kr.jenna.plmography.dtos.review.ReviewDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.vo.ReviewBody;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchReviewServiceTest {

    @Test
    void update() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ReviewCommentRepository reviewCommentRepository = mock(ReviewCommentRepository.class);
        PatchReviewService patchReviewService = new PatchReviewService(reviewRepository, reviewCommentRepository);

        given(reviewRepository.getReferenceById(any(Long.class)))
                .willReturn(Review.fake());

        ReviewDto reviewDto = ReviewDto.fake();

        Review review = patchReviewService.modify(1L, 1L, 3L, new ReviewBody(reviewDto.getReviewBody()));

        assertThat(Review.fake().getReviewBody().getValue())
                .isNotEqualTo(reviewDto.getReviewBody());

        assertThat(Review.fake().getStarRate())
                .isNotEqualTo(reviewDto.getStarRate());

        assertThat(review.getReviewBody().getValue())
                .isEqualTo(reviewDto.getReviewBody());

        assertThat(review.getStarRate())
                .isEqualTo(reviewDto.getStarRate());
    }
}
