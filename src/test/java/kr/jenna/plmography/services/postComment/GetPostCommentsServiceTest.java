package kr.jenna.plmography.services.postComment;

import kr.jenna.plmography.dtos.postComment.MyPostCommentsDto;
import kr.jenna.plmography.dtos.postComment.PostCommentsDto;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.PostCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostCommentsServiceTest {
    private PostCommentRepository postCommentRepository;
    private UserRepository userRepository;
    private GetPostCommentsService getPostCommentsService;

    @BeforeEach
    void setup() {
        postCommentRepository = mock(PostCommentRepository.class);
        userRepository = mock(UserRepository.class);
        getPostCommentsService = new GetPostCommentsService(
                postCommentRepository, userRepository);
    }

    @Test
    void list() {
        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        given(postCommentRepository.findAllByPostId(any(), any()))
                .willReturn(new PageImpl<>(List.of(PostComment.fake())));

        Long postId = 1L;
        Integer page = 1;
        Integer size = 3;

        PostCommentsDto postCommentsDto = getPostCommentsService.list(postId, page, size);

        assertThat(postCommentsDto).isNotNull();
        assertThat(postCommentsDto.getPostComments().get(0).getPostCommentBody())
                .isEqualTo("reply");
    }

    @Test
    void myReview() {
        given(postCommentRepository.findAllByUserId(new UserId(1L)))
                .willReturn(List.of(PostComment.fake()));

        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));

        MyPostCommentsDto commentsDto = getPostCommentsService.myComments(1L);

        assertThat(commentsDto).isNotNull();
    }
}
