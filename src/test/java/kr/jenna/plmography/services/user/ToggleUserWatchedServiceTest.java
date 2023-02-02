package kr.jenna.plmography.services.user;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ToggleUserWatchedServiceTest {

    @Test
    void toggleWatched() {
        UserRepository userRepository = mock(UserRepository.class);
        ContentRepository contentRepository = mock(ContentRepository.class);
        ToggleUserWatchedService toggleUserWatchedService
                = new ToggleUserWatchedService(userRepository, contentRepository);

        User user = User.fake();
        Content content = Content.fake();

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(contentRepository.findByTmdbId(any())).willReturn(Optional.of(content));

        Long userId = 1L;
        WatchedContentId watchedContentId = new WatchedContentId(1L);

        toggleUserWatchedService.toggleWatched(userId, watchedContentId);

        assertThat(user.getWatchedContentIds()).hasSize(1);

        toggleUserWatchedService.toggleWatched(userId, watchedContentId);

        assertThat(user.getWatchedContentIds()).hasSize(0);
    }
}
