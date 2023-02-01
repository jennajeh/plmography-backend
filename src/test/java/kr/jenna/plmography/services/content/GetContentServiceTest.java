package kr.jenna.plmography.services.content;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetContentServiceTest {
    private GetContentService getContentService;
    private ContentRepository contentRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        contentRepository = mock(ContentRepository.class);
        userRepository = mock(UserRepository.class);
        getContentService = new GetContentService(userRepository, contentRepository);
    }

    @Test
    void detail() {
        given(contentRepository.findByTmdbId(any()))
                .willReturn(Optional.of(Content.fake()));

        ContentDto contentDto = getContentService.detail(1L);

        verify(contentRepository).findByTmdbId(1L);

        assertThat(contentDto.getKorTitle()).isEqualTo("아바타");
        assertThat(contentDto.getEngTitle()).isEqualTo("Avatar");
    }
}
