package kr.jenna.plmography.services.review;

import kr.jenna.plmography.dtos.review.ReviewRegistrationDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateReviewServiceTest {

    @Test
    void create() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        CreateReviewService createReviewService = new CreateReviewService(reviewRepository, userRepository);

        User user = User.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        ReviewRegistrationDto reviewRegistrationDto = new ReviewRegistrationDto(
                1L, 4L, "영화가 재미있어요!");

        Review review = createReviewService.create(1L, reviewRegistrationDto);

        assertThat(review).isNotNull();

        verify(reviewRepository).save(any(Review.class));
    }

}
