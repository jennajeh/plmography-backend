package kr.jenna.plmography.services.review;

import kr.jenna.plmography.dtos.review.MyReviewsDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewServiceTest {

    @Test
    void myReview() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetReviewService getReviewService = new GetReviewService(reviewRepository, userRepository);

        given(reviewRepository.findByUserId(new UserId(1L))).willReturn(Optional.of(Review.fake()));
        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        MyReviewsDto reviewDto = getReviewService.myReview(1L);

        assertThat(reviewDto).isNotNull();
    }
}
