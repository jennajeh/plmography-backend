package kr.jenna.plmography.services.comment;

import kr.jenna.plmography.dtos.comment.CommentRegistrationDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {

    @Test
    void create() {
        UserRepository userRepository = mock(UserRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CreateCommentService createCommentService =
                new CreateCommentService(userRepository, commentRepository);

        User user = User.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        CommentRegistrationDto commentRegistrationDto =
                new CommentRegistrationDto(1L, 1L, "동의합니다~");

        Comment comment = createCommentService.create(1L, commentRegistrationDto);

        assertThat(comment).isNotNull();

        verify(commentRepository).save(comment);
    }
}
