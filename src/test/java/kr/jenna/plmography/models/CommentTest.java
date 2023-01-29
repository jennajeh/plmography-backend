package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.comment.CommentCreationDto;
import kr.jenna.plmography.dtos.comment.CommentDto;
import kr.jenna.plmography.models.vo.CommentBody;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    void creation() {
        Comment comment = new Comment(1L, new UserId(1L), new PostId(1L), new CommentBody("reply"));

        assertThat(comment.getCommentBody()).isEqualTo(new CommentBody("reply"));
    }

    @Test
    void modify() {
        Comment comment = Comment.fake();

        CommentDto commentDto = CommentDto.fake();

        comment.modify(new CommentBody(commentDto.getCommentBody()));

        assertThat(comment.getCommentBody().getValue()).isEqualTo(commentDto.getCommentBody());
    }

    @Test
    void delete() {
        Comment comment = Comment.fake();

        comment.delete();

        assertThat(comment.isDeleted()).isTrue();
    }

    @Test
    void toCreationDto() {
        Comment comment = Comment.fake();

        CommentCreationDto commentCreationDto = comment.toCreateDto();

        assertThat(commentCreationDto).isNotNull();
        assertThat(commentCreationDto.getId()).isEqualTo(comment.getId());
    }

    @Test
    void isWriter() {
        Comment comment = Comment.fake();

        assertThat(comment.isWriter(1L)).isTrue();
        assertThat(comment.isWriter(2L)).isFalse();
    }
}
