package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Review.ReviewsDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.UserId;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.services.Review.GetReviewsService;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewsServiceTest {

    @Test
    void list() {
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetReviewsService getReviewsService = new GetReviewsService(reviewRepository, userRepository);

        List<Review> reviews = Review.fakes(5);

        given(reviewRepository.findAllByUserIdNotLike(any(UserId.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(reviews));

        given(userRepository.findById(any())).willReturn(Optional.of(User.fake()));

        Integer page = 1;
        Integer size = 3;

        ReviewsDto reviewsDto =
                getReviewsService.reviews(1L, page, size);

        assertThat(reviewsDto).isNotNull();
        assertThat(reviewsDto.getReviews().get(0).getReviewBody()).isEqualTo("영화가 재미있어요 1");
        assertThat(reviewsDto.getReviews().get(1).getReviewBody()).isEqualTo("영화가 재미있어요 2");
    }
}
