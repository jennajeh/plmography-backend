package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.RecommentRepository;
import kr.jenna.plmography.services.Comment.DeleteCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private RecommentRepository recommentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository, recommentRepository);
    }

    @Test
    void delete() {
        Comment comment = Comment.fake();

        given(commentRepository.findById(any()))
                .willReturn(Optional.of(comment));

        Long userId = 1L;
        Long commentId = 1L;

        assertDoesNotThrow(() -> deleteCommentService.delete(userId, commentId));

        assertThat(comment.isDeleted()).isTrue();
    }
}
