package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.Comment.CommentsDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.services.Comment.GetCommentsService;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {

    @Test
    void list() {
        CommentRepository commentRepository = mock(CommentRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetCommentsService getCommentsService = new GetCommentsService(
                commentRepository, userRepository);

        Comment comment = Comment.fake();

        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        given(commentRepository.findAllByUserId(any(), any()))
                .willReturn(new PageImpl<>(List.of(comment)));

        Integer page = 1;
        Integer size = 1;

        CommentsDto commentsDto =
                getCommentsService.comments(
                        comment.getUserId().getValue(), page, size);

        assertThat(commentsDto).isNotNull();
        assertThat(commentsDto.getComments().get(0).getCommentBody())
                .isEqualTo("reply");
    }
}
