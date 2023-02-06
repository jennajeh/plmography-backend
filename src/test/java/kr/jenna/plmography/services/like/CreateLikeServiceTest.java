package kr.jenna.plmography.services.like;

import kr.jenna.plmography.dtos.like.LikeDto;
import kr.jenna.plmography.models.Like;
import kr.jenna.plmography.repositories.LikeRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateLikeServiceTest {

    @Test
    void countLike() {
        LikeRepository likeRepository = mock(LikeRepository.class);
        CreateLikeService createLikeService = new CreateLikeService(likeRepository);

        createLikeService.countLike(new LikeDto(1L, 1L, 1L));

        verify(likeRepository).save(any(Like.class));
    }
}
