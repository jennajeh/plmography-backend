package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.MyPostsDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {

    @Test
    void myPost() {
        PostRepository postRepository = mock(PostRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetPostService getPostService = new GetPostService(postRepository, userRepository);

        given(postRepository.findAllByUserId(new UserId(1L))).willReturn(List.of(Post.fake()));
        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        MyPostsDto postsDto = getPostService.myPost(1L);

        assertThat(postsDto).isNotNull();
        assertThat(postsDto.getMyPosts().get(0).getTitle()).isEqualTo("제목");
    }
}
