package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Review.ReviewDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.ReviewBody;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.services.Review.PatchReviewService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchReviewServiceTest {

    @Test
    void update() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        PatchReviewService patchReviewService = new PatchReviewService(reviewRepository, commentRepository);

        given(reviewRepository.findById(any(Long.class))).willReturn(Optional.of(Review.fake()));

        ReviewDto reviewDto = ReviewDto.fake();

        Review review = patchReviewService.modify(1L, 1L, new ReviewBody(reviewDto.getReviewBody()));

        assertThat(Review.fake().getReviewBody().getValue())
                .isNotEqualTo(reviewDto.getReviewBody());

        assertThat(review.getReviewBody().getValue())
                .isEqualTo(reviewDto.getReviewBody());
    }
}
