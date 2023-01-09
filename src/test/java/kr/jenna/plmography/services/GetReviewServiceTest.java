package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Review.ReviewDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.services.Review.GetReviewService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewServiceTest {

    @Test
    void detail() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetReviewService getReviewService = new GetReviewService(reviewRepository, userRepository);

        given(reviewRepository.findById(1L)).willReturn(Optional.of(Review.fake()));
        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        ReviewDto reviewDto = getReviewService.detail(1L);

        assertThat(reviewDto).isNotNull();
        assertThat(reviewDto.getReviewBody()).isEqualTo("영화가 재미있어요");
    }
}
