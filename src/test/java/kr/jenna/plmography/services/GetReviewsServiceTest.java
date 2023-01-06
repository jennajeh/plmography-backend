package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.ReviewsDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewsServiceTest {

    @Test
    void list() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        GetReviewsService getReviewsService = new GetReviewsService(reviewRepository);

        Review review = Review.fake();

        given(reviewRepository.findAllByUserId(any(), any()))
                .willReturn(new PageImpl<>(List.of(review)));

        Integer page = 1;
        Integer size = 3;

        ReviewsDto reviewsDto =
                getReviewsService.reviews(review.getUserId().getValue(), page, size);

        assertThat(reviewsDto).isNotNull();
        assertThat(reviewsDto.getReviews().get(0).getReviewBody()).isEqualTo("영화가 재미있어요");
    }
}
