package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.VO.CommentBody;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.services.Comment.PatchCommentService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchCommentServiceTest {

    @Test
    void update() {
        CommentRepository commentRepository = mock(CommentRepository.class);
        PatchCommentService patchCommentService = new PatchCommentService(commentRepository);

        given(commentRepository.findById(any(Long.class))).willReturn(Optional.of(Comment.fake()));

        Long commentId = 1L;
        CommentDto commentDto = CommentDto.fake();

        Comment comment = patchCommentService.modify(1L, commentId, new CommentBody(commentDto.getCommentBody()));

        assertThat(Comment.fake().getCommentBody().getValue())
                .isNotEqualTo(commentDto.getCommentBody());

        assertThat(comment.getCommentBody().getValue()).isEqualTo(commentDto.getCommentBody());
    }
}
