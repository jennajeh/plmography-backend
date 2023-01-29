package kr.jenna.plmography.services.content;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.WatchedUserId;
import kr.jenna.plmography.repositories.ContentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ToggleContentWatchedServiceTest {

    @Test
    void toggleWatched() {
        ContentRepository contentRepository = mock(ContentRepository.class);
        ToggleContentWatchedService toggleContentWatchedService = new ToggleContentWatchedService(contentRepository);

        Content content = Content.fake();

        given(contentRepository.findById(any()))
                .willReturn(Optional.of(content));

        ContentId contentId = new ContentId(1L);
        WatchedUserId watchedUserId = new WatchedUserId(1L);

        toggleContentWatchedService.toggleWatched(contentId, watchedUserId);

        assertThat(content.getWatchedUserIds()).hasSize(1);

        toggleContentWatchedService.toggleWatched(contentId, watchedUserId);

        assertThat(content.getWatchedUserIds()).hasSize(0);
    }
}
