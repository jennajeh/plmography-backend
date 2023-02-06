package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.PostsDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private GetPostsService getPostsService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);
        commentRepository = mock(CommentRepository.class);
        getPostsService = new GetPostsService(
                postRepository, commentRepository, userRepository);
    }

    @Test
    void list() {
        given(postRepository.findAll(any(Specification.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(Post.fake())));

        given(userRepository.findById(any())).willReturn(Optional.of(User.fake()));

        given(commentRepository.findAllByPostId(any())).willReturn(List.of(Comment.fake()));

        Integer page = 1;
        Integer size = 3;

        PostsDto postsDto = getPostsService.list("키워드", page, size);

        assertThat(postsDto).isNotNull();
        assertThat(postsDto.getPosts().get(0).getPostBody()).isEqualTo("첫 글 작성");
    }

    @Test
    void top3Hit() {
        given(postRepository.findTop3ByOrderByHitDesc()).willReturn(List.of(Post.fake()));
        given(userRepository.findById(any())).willReturn(Optional.of(User.fake()));

        assertThat(getPostsService.top3Hit().getPosts()).isNotNull();
        assertThat(getPostsService.top3Hit().getPosts().get(0).getPostBody()).isEqualTo("첫 글 작성");
    }
}
