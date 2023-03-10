package kr.jenna.plmography.services.review;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteReviewServiceTest {
    private ReviewRepository reviewRepository;
    private ReviewCommentRepository reviewCommentRepository;
    private DeleteReviewService deleteReviewService;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        reviewCommentRepository = mock(ReviewCommentRepository.class);
        deleteReviewService = new DeleteReviewService(reviewRepository, reviewCommentRepository);
    }

    @Test
    void delete() {
        Review review = Review.fake();

        given(reviewRepository.getReferenceById(any())).willReturn(review);

        Long userId = 1L;
        Long reviewId = 1L;

        assertDoesNotThrow(() -> deleteReviewService.delete(userId, reviewId));

        assertThat(review.getDeleted()).isTrue();
    }
}
