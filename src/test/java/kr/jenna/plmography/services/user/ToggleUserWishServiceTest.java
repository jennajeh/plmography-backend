package kr.jenna.plmography.services.user;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.WishContentId;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ToggleUserWishServiceTest {

    @Test
    void toggleWish() {
        UserRepository userRepository = mock(UserRepository.class);
        ContentRepository contentRepository = mock(ContentRepository.class);
        ToggleUserWishService toggleUserWishService
                = new ToggleUserWishService(userRepository, contentRepository);

        User user = User.fake();
        Content content = Content.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(contentRepository.findByTmdbId(any())).willReturn(Optional.of(content));

        Long userId = 1L;
        WishContentId wishContentId = new WishContentId(1L);

        toggleUserWishService.toggleWish(userId, wishContentId);

        assertThat(user.getWishContentIds()).hasSize(1);

        toggleUserWishService.toggleWish(userId, wishContentId);

        assertThat(user.getWishContentIds()).hasSize(0);
    }
}
