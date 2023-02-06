package kr.jenna.plmography.services.like;

import kr.jenna.plmography.dtos.like.LikesDto;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.repositories.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetLikesServiceTest {
    private LikeRepository likeRepository;
    private GetLikesService getLikesService;
    private Like like;

    @BeforeEach
    void setup() {
        likeRepository = mock(LikeRepository.class);
        getLikesService = new GetLikesService(likeRepository);
        like = Like.fake();
    }

    @Test
    void likes() {
        given(likeRepository.findAll()).willReturn(List.of(like));

        LikesDto likesDto = getLikesService.likes();

        assertThat(likesDto).isNotNull();
        assertThat(likesDto.getLikes().size()).isEqualTo(1);
    }

    @Test
    void findLike() {
        given(likeRepository.findAllByPostId(any())).willReturn(List.of(like));

        List<Like> foundLike = getLikesService.findLike(any(Long.class));

        assertThat(foundLike).isNotNull();
        assertThat(foundLike).hasSize(1);
    }
}
