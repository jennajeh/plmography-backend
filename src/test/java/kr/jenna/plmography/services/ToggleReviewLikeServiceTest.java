package kr.jenna.plmography.services;

import kr.jenna.plmography.models.LikeUserId;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.ReviewId;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ToggleReviewLikeServiceTest {

    @Test
    void toggleLike() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ToggleReviewLikeService toggleReviewLikeService = new ToggleReviewLikeService(reviewRepository);

        Review review = Review.fake();

        given(reviewRepository.findById(any()))
                .willReturn(Optional.of(review));

        ReviewId reviewId = new ReviewId(1L);
        LikeUserId likeUserId = new LikeUserId(1L);

        toggleReviewLikeService.toggleLike(reviewId, likeUserId);

        assertThat(review.getLikeUserIds()).hasSize(1);

        toggleReviewLikeService.toggleLike(reviewId, likeUserId);

        assertThat(review.getLikeUserIds()).hasSize(0);
    }
}
