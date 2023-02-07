package kr.jenna.plmography.services.post;

import kr.jenna.plmography.dtos.post.PostModificationRequestDto;
import kr.jenna.plmography.dtos.post.PostModificationResponseDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.PostBody;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchPostServiceTest {
    private PostRepository postRepository;
    private PatchPostService patchPostService;
    private Post post;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        patchPostService = new PatchPostService(postRepository);
        post = Post.fake();
    }

    @Test
    void modify() {
        given(postRepository.getReferenceById(any(Long.class))).willReturn(post);

        Long userId = 1L;
        Long postId = 1L;
        Title title = new Title("제목 수정");
        PostBody postBody = new PostBody(post.getPostBody().getValue());
        Image image = new Image("");

        PostModificationRequestDto fake = new PostModificationRequestDto(
                title.getValue(), postBody.getValue(), image.getValue());

        PostModificationResponseDto response =
                patchPostService.modify(userId, postId, fake);

        assertThat(post.getTitle().getValue()).isEqualTo(title.getValue());
    }
}
