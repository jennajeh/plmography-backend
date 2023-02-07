package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.repositories.PostCommentRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeletePostCommentServiceTest {

    @Test
    void delete() {
        PostCommentRepository postCommentRepository = mock(PostCommentRepository.class);
        DeletePostCommentService deletePostCommentService = new DeletePostCommentService(postCommentRepository);

        PostComment postComment = PostComment.fake();
        given(postCommentRepository.getReferenceById(any())).willReturn(postComment);

        Long userId = 1L;
        Long postId = 1L;

        assertDoesNotThrow(() -> deletePostCommentService.delete(userId, postId));

        assertThat(postComment.isDeleted()).isTrue();
    }
}
