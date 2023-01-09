package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.services.Content.GetContentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetContentsServiceTest {
    private GetContentsService getContentsService;
    private ContentRepository contentRepository;

    @BeforeEach
    void setUp() {
        contentRepository = mock(ContentRepository.class);
        getContentsService = new GetContentsService(contentRepository);
    }

    @Test
    void list() {
        Content content = mock(Content.class);

        given(contentRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(content)));

        Integer page = 1;
        Integer size = 8;

        assertThat(getContentsService.list(page, size)).isNotNull();
        assertThat(getContentsService.list(page, size)).hasSize(1);
    }
}
