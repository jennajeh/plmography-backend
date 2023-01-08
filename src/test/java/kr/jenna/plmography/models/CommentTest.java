package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.CommentCreationDto;
import kr.jenna.plmography.dtos.CommentDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    void creation() {
        Comment comment = new Comment(1L, new UserId(1L),
                new PostId(1L), new CommentBody("reply"), LocalDateTime.now());

        assertThat(comment.getCommentBody()).isEqualTo(new CommentBody("reply"));
    }

    @Test
    void modify() {
        Comment comment = Comment.fake();

        String body = "동의합니다~";

        comment.modify(new CommentBody(body));

        assertThat(comment.getCommentBody()).isEqualTo(new CommentBody(body));
    }

    @Test
    void delete() {
        Comment comment = Comment.fake();

        comment.delete();

        assertThat(comment.isDeleted()).isTrue();
    }

    @Test
    void toCommentDto() {
        Comment comment = Comment.fake();

        CommentDto commentDto = comment.toCommentDto();

        assertThat(commentDto).isNotNull();
        assertThat(commentDto.getCommentBody())
                .isEqualTo(comment.getCommentBody().getValue());
    }

    @Test
    void toCreationDto() {
        Comment comment = Comment.fake();

        CommentCreationDto commentCreationDto = comment.toCreateDto();

        assertThat(commentCreationDto).isNotNull();
        assertThat(commentCreationDto.getId()).isEqualTo(comment.getId());
    }
}
