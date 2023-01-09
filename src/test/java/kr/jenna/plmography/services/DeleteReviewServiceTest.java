package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.PostId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.services.Review.DeleteReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteReviewServiceTest {
    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;
    private DeleteReviewService deleteReviewService;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        commentRepository = mock(CommentRepository.class);
        deleteReviewService = new DeleteReviewService(reviewRepository, commentRepository);
    }

    @Test
    void delete() {
        given(reviewRepository.findById(any()))
                .willReturn(Optional.of(Review.fake()));

        Long userId = 1L;
        PostId postId = new PostId(1L);

        assertDoesNotThrow(() -> deleteReviewService.delete(userId, postId));

        verify(reviewRepository).delete(any(Review.class));
    }
}
