package kr.jenna.plmography.services.content;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetContentsServiceTest {
    private GetContentsService getContentsService;
    private ContentRepository contentRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        contentRepository = mock(ContentRepository.class);
        userRepository = mock(UserRepository.class);
        getContentsService = new GetContentsService(contentRepository, userRepository);
    }

    @Test
    void topRated() {
        Content content = Content.fake();

        given(contentRepository.findAllByPopularityGreaterThanOrderByPopularityDesc(any(Integer.class)))
                .willReturn(List.of(content));

        assertThat(getContentsService.topRated().getContents()).isNotNull();
    }
}
