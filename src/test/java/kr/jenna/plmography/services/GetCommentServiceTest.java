package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.services.Comment.GetCommentService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentServiceTest {

    @Test
    void detail() {
        CommentRepository commentRepository = mock(CommentRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetCommentService getCommentService =
                new GetCommentService(commentRepository, userRepository);

        given(commentRepository.findById(1L)).willReturn(Optional.of(Comment.fake()));
        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        CommentDto commentDto = getCommentService.detail(1L);

        assertThat(commentDto).isNotNull();
        assertThat(commentDto.getCommentBody()).isEqualTo("reply");
    }

}
