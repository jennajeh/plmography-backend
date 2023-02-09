package kr.jenna.plmography.services.reviewComment;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentRegistrationDto;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateReviewCommentServiceTest {

    @Test
    void create() {
        UserRepository userRepository = mock(UserRepository.class);
        ReviewCommentRepository reviewCommentRepository = mock(ReviewCommentRepository.class);
        CreateReviewCommentService createReviewCommentService =
                new CreateReviewCommentService(userRepository, reviewCommentRepository);

        User user = User.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        ReviewCommentRegistrationDto commentRegistrationDto =
                new ReviewCommentRegistrationDto(1L, 1L, "동의합니다~");

        ReviewComment reviewComment = createReviewCommentService.create(1L, commentRegistrationDto);

        assertThat(reviewComment).isNotNull();

        verify(reviewCommentRepository).save(reviewComment);
    }
}
