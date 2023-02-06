package kr.jenna.plmography.services.post;

import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeletePostServiceTest {
    @Test
    void delete() {
        PostRepository postRepository = mock(PostRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        DeletePostService deletePostService =
                new DeletePostService(postRepository, commentRepository);

        Post post = Post.fake();

        given(postRepository.getReferenceById(any())).willReturn(post);

        Long userId = 1L;
        Long postId = 1L;

        assertDoesNotThrow(() -> deletePostService.delete(userId, postId));

        assertThat(post.getDeleted()).isTrue();
    }
}
