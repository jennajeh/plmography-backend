package kr.jenna.plmography.services.Content;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.VO.ContentId;
import kr.jenna.plmography.models.VO.WishUserId;
import kr.jenna.plmography.repositories.ContentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ToggleContentWishServiceTest {

    @Test
    void toggleWish() {
        ContentRepository contentRepository = mock(ContentRepository.class);
        ToggleContentWishService toggleContentWishService = new ToggleContentWishService(contentRepository);

        Content content = Content.fake();

        given(contentRepository.findById(any()))
                .willReturn(Optional.of(content));

        ContentId contentId = new ContentId(1L);
        WishUserId wishUserId = new WishUserId(1L);

        toggleContentWishService.toggleWish(contentId, wishUserId);

        assertThat(content.getWishUserIds()).hasSize(1);

        toggleContentWishService.toggleWish(contentId, wishUserId);

        assertThat(content.getWishUserIds()).hasSize(0);
    }
}
