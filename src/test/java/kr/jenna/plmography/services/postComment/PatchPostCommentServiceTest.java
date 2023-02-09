package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.dtos.postComment.PostCommentDto;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.vo.PostCommentBody;
import kr.jenna.plmography.repositories.PostCommentRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchPostCommentServiceTest {

    @Test
    void modify() {
        PostCommentRepository postCommentRepository = mock(PostCommentRepository.class);
        PatchPostCommentService patchPostCommentService =
                new PatchPostCommentService(postCommentRepository);

        given(postCommentRepository.getReferenceById(any(Long.class)))
                .willReturn(PostComment.fake());

        Long userId = 1L;
        Long commentId = 1L;
        PostCommentBody postBody = new PostCommentBody("수정합니다");

        PostCommentDto postCommentDto = PostCommentDto.fake();

        PostComment postComment =
                patchPostCommentService.modify(userId, commentId, postBody);

        assertThat(PostComment.fake().getPostCommentBody().getValue())
                .isNotEqualTo(postCommentDto.getPostCommentBody());

        assertThat(postComment.getPostCommentBody().getValue()).isEqualTo("수정합니다");
    }
}
