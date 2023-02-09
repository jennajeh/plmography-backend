package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.postComment.PostCommentCreationDto;
import kr.jenna.plmography.dtos.postComment.PostCommentDto;
import kr.jenna.plmography.models.vo.PostCommentBody;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostCommentTest {
    private PostComment postComment;

    @BeforeEach
    void setup() {
        postComment = PostComment.fake();
    }

    @Test
    void creation() {
        PostComment comment = new PostComment(
                1L, new UserId(1L), new PostId(1L), new PostCommentBody("reply"));

        assertThat(comment.getPostCommentBody()).isEqualTo(new PostCommentBody("reply"));
    }

    @Test
    void modify() {
        PostCommentDto commentDto = PostCommentDto.fake();

        postComment.modify(new PostCommentBody(commentDto.getPostCommentBody()));

        assertThat(postComment.getPostCommentBody().getValue())
                .isEqualTo(commentDto.getPostCommentBody());
    }

    @Test
    void delete() {
        postComment.delete();

        assertThat(postComment.isDeleted()).isTrue();
    }

    @Test
    void toCreationDto() {
        PostCommentCreationDto commentCreationDto = postComment.toCreateDto();

        assertThat(commentCreationDto).isNotNull();
        assertThat(commentCreationDto.getId()).isEqualTo(postComment.getId());
    }

    @Test
    void isWriter() {
        assertThat(postComment.isWriter(1L)).isTrue();
        assertThat(postComment.isWriter(2L)).isFalse();
    }
}
