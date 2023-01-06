package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.ReviewRegistrationDto;
import kr.jenna.plmography.models.Review;
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
        PatchReviewService patchReviewService = new PatchReviewService(reviewRepository);

        Review review = Review.fake();

        given(reviewRepository.getReferenceById(any(Long.class))).willReturn(review);

        ReviewRegistrationDto reviewRegistrationDto =
                new ReviewRegistrationDto(review.getId(), review.getUserId().getValue(),
                        review.getContentId().getValue(), review.getStarRate(),
                        "살짝 아쉽네요");

        patchReviewService.update(reviewRegistrationDto, review.getId());

        assertThat(Review.fake().getReviewBody().getValue())
                .isNotEqualTo(reviewRegistrationDto.getReviewBody());
        assertThat(review.getReviewBody().getValue())
                .isEqualTo(reviewRegistrationDto.getReviewBody());

    }

}
