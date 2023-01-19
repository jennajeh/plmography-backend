package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.services.Review.DeleteReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteReviewServiceTest {
    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;
    private DeleteReviewService deleteReviewService;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        commentRepository = mock(CommentRepository.class);
        deleteReviewService = new DeleteReviewService(reviewRepository, commentRepository);
    }

    @Test
    void delete() {
        Review review = Review.fake();

        given(reviewRepository.findById(any()))
                .willReturn(Optional.of(review));

        Long userId = 1L;
        Long reviewId = 1L;

        assertDoesNotThrow(() -> deleteReviewService.delete(userId, reviewId));

        assertThat(review.getDeleted()).isTrue();
    }
}
