package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.PostRegistrationDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.PostRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {

    @Test
    void create() {
        PostRepository postRepository = mock(PostRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        CreatePostService createPostService = new CreatePostService(postRepository, userRepository);

        User user = User.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        PostRegistrationDto postRegistrationDto = new PostRegistrationDto(
                "오늘의 영화는", "오늘 아바타를 보고 왔습니다.", "image.jpg");

        Post post = createPostService.create(1L, postRegistrationDto);

        assertThat(post).isNotNull();
        
        verify(postRepository).save(any(Post.class));
    }
}
