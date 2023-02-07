package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.dtos.postComment.PostCommentRegistrationDto;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostCommentServiceTest {

    @Test
    void create() {
        UserRepository userRepository = mock(UserRepository.class);
        PostCommentRepository postCommentRepository = mock(PostCommentRepository.class);
        CreatePostCommentService createPostCommentService =
                new CreatePostCommentService(userRepository, postCommentRepository);

        User user = User.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        PostCommentRegistrationDto postCommentRegistrationDto =
                new PostCommentRegistrationDto(1L, "동의합니다~");

        PostComment postComment = createPostCommentService.create(1L, postCommentRegistrationDto);

        assertThat(postComment).isNotNull();

        verify(postCommentRepository).save(postComment);
    }
}
