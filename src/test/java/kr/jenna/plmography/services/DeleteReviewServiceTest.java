package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteReviewServiceTest {
    private ReviewRepository reviewRepository;
    private DeleteReviewService deleteReviewService;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        deleteReviewService = new DeleteReviewService(reviewRepository);
    }

    @Test
    void deleteReview() {
        Review review = Review.fake();

        given(reviewRepository.getReferenceById(1L))
                .willReturn(review);

        deleteReviewService.delete(1L);

        verify(reviewRepository).delete(review);
    }


}
