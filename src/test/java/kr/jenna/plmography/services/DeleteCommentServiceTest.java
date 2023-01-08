package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private RecommentRepository recommentRepository;
    private DeleteCommentService deleteCommentService;
    private Comment comment;

    @BeforeEach
    void setup() {
        comment = Comment.fake();
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);
        deleteCommentService =
                new DeleteCommentService(commentRepository, recommentRepository);

        given(commentRepository.getReferenceById(comment.getId())).willReturn(comment);
    }

    @Test
    void deleteStatusFalse() {
        deleteCommentService.delete(comment.getId());

        verify(commentRepository).delete(comment);

        assertThat(comment.isDeleted()).isFalse();
    }

    @Test
    void deleteStatusTrue() {
        given(recommentRepository.existsByCommentId(any(Long.class))).willReturn(true);

        deleteCommentService.delete(comment.getId());

        assertThat(comment.isDeleted()).isTrue();
    }
}
