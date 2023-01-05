package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewServiceTest {

    @Test
    void detail() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        GetReviewService getReviewService = new GetReviewService(reviewRepository);

        given(reviewRepository.findById(1L)).willReturn(Optional.of(Review.fake()));

        Review review = getReviewService.detail(1L);

        assertThat(review).isNotNull();
    }

}
