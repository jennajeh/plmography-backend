package kr.jenna.plmography.services.reviewComment;

import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteReviewCommentServiceTest {
    private ReviewCommentRepository reviewCommentRepository;
    private DeleteReviewCommentService deleteReviewCommentService;

    @BeforeEach
    void setup() {
        reviewCommentRepository = mock(ReviewCommentRepository.class);
        deleteReviewCommentService = new DeleteReviewCommentService(reviewCommentRepository);
    }

    @Test
    void delete() {
        ReviewComment reviewComment = ReviewComment.fake();

        given(reviewCommentRepository.findById(any()))
                .willReturn(Optional.of(reviewComment));

        Long userId = 1L;
        Long commentId = 1L;

        assertDoesNotThrow(() -> deleteReviewCommentService.delete(userId, commentId));

        assertThat(reviewComment.isDeleted()).isTrue();
    }
}
