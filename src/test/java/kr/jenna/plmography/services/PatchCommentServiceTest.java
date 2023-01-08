package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.repositories.CommentRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchCommentServiceTest {

    @Test
    void update() {
        CommentRepository commentRepository = mock(CommentRepository.class);
        PatchCommentService patchCommentService = new PatchCommentService(commentRepository);

        Comment comment = Comment.fake();

        given(commentRepository.getReferenceById(any(Long.class)))
                .willReturn(comment);

        patchCommentService.update(comment.getId(), "놀랍네요!!!");

        assertThat(Comment.fake().getCommentBody()).isNotEqualTo("놀랍네요!!!");
        assertThat(comment.getCommentBody().getValue()).isEqualTo("놀랍네요!!!");
    }
}
