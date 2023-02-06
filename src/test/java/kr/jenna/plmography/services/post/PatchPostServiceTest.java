package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.UpdateHitPostResponseDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.repositories.PostRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchPostServiceTest {

    @Test
    void updateHit() {
        PostRepository postRepository = mock(PostRepository.class);
        PatchPostService patchPostService = new PatchPostService(postRepository);

        Post post = Post.fake();

        given(postRepository.getReferenceById(1L)).willReturn(post);

        UpdateHitPostResponseDto updateHitPostResponseDto =
                patchPostService.updateHit(1L);

        assertThat(post.getHit().getValue()).isEqualTo(1L);
    }
}
