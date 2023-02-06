package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.SelectedPostsDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.LikeRepository;
import kr.jenna.plmography.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeletePostServiceTest {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private LikeRepository likeRepository;
    private DeletePostService deletePostService;
    private Post post;

    @BeforeEach
    void setup() {
        post = Post.fake();

        postRepository = mock(PostRepository.class);
        likeRepository = mock(LikeRepository.class);
        commentRepository = mock(CommentRepository.class);
        deletePostService =
                new DeletePostService(postRepository, commentRepository, likeRepository);
    }

    @Test
    void delete() {
        given(postRepository.getReferenceById(any())).willReturn(post);

        Long userId = 1L;
        Long postId = 1L;

        assertDoesNotThrow(() -> deletePostService.delete(userId, postId));

        assertThat(post.getDeleted()).isTrue();
    }

    @Test
    void deletePosts() {
        List<Long> postIds = new ArrayList<>();

        postIds.add(1L);
        postIds.add(2L);

        given(postRepository.getReferenceById(any())).willReturn(post);

        deletePostService.deletePosts(SelectedPostsDto.fake());

        assertThat(post.getDeleted()).isTrue();
    }
}
