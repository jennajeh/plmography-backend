package kr.jenna.plmography.services.post;

import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.repositories.LikeRepository;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeletePostServiceTest {
    private PostRepository postRepository;
    private PostCommentRepository postCommentRepository;
    private LikeRepository likeRepository;
    private DeletePostService deletePostService;
    private Post post;

    @BeforeEach
    void setup() {
        post = Post.fake();

        postRepository = mock(PostRepository.class);
        likeRepository = mock(LikeRepository.class);
        postCommentRepository = mock(PostCommentRepository.class);
        deletePostService =
                new DeletePostService(postRepository, postCommentRepository, likeRepository);
    }

    @Test
    void delete() {
        given(postRepository.getReferenceById(any())).willReturn(post);

        Long userId = 1L;
        Long postId = 1L;

        assertDoesNotThrow(() -> deletePostService.delete(userId, postId));

        assertThat(post.getDeleted()).isTrue();
    }
}
