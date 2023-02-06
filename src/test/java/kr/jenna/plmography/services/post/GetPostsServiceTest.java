package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.PostsDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
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

    @Test
    void list() {
        PostRepository postRepository = mock(PostRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        GetPostsService getPostsService = new GetPostsService(
                postRepository, commentRepository, userRepository);

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

}
