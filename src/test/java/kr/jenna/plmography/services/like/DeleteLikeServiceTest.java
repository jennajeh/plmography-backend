package kr.jenna.plmography.services.like;

import kr.jenna.plmography.dtos.like.SelectedLikeDto;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.LikeRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteLikeServiceTest {

    @Test
    void deleteLike() {
        Like like = Like.fake();

        LikeRepository likeRepository = mock(LikeRepository.class);
        DeleteLikeService deleteLikeService = new DeleteLikeService(likeRepository);

        deleteLikeService.delete(SelectedLikeDto.fake());

        verify(likeRepository)
                .deleteByPostId(new PostId(SelectedLikeDto.fake().getLikeId().get(0)));
    }
}
