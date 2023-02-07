package kr.jenna.plmography.services.reviewComment;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentsDto;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetReviewCommentsServiceTest {

    @Test
    void list() {
        ReviewCommentRepository reviewCommentRepository = mock(ReviewCommentRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetReviewCommentsService getReviewCommentsService = new GetReviewCommentsService(
                reviewCommentRepository, userRepository);

        ReviewComment reviewComment = ReviewComment.fake();

        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        given(reviewCommentRepository.findAll()).willReturn(List.of(reviewComment));

        ReviewCommentsDto reviewCommentsDto =
                getReviewCommentsService.list();

        assertThat(reviewCommentsDto).isNotNull();
        assertThat(reviewCommentsDto.getReviewComments().get(0).getReviewCommentBody())
                .isEqualTo("reply");
    }
}
