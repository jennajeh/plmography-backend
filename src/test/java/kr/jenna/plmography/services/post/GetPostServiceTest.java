package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.MyPostsDto;
import kr.jenna.plmography.dtos.post.PostDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private PostCommentRepository postCommentRepository;
    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        postCommentRepository = mock(PostCommentRepository.class);
        userRepository = mock(UserRepository.class);
        getPostService = new GetPostService(postRepository, postCommentRepository, userRepository);
    }

    @Test
    void myPost() {
        given(postRepository.findAllByUserId(new UserId(1L))).willReturn(List.of(Post.fake()));
        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        MyPostsDto postsDto = getPostService.myPost(1L);

        assertThat(postsDto).isNotNull();
        assertThat(postsDto.getMyPosts().get(0).getTitle()).isEqualTo("제목");
    }

    @Test
    void detail() {
        given(postRepository.findById(any())).willReturn(Optional.of(Post.fake()));
        given(userRepository.findById(any())).willReturn(Optional.of(User.fake()));

        PostDto postDto = getPostService.detail(1L);

        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).isEqualTo("제목");
    }
}
