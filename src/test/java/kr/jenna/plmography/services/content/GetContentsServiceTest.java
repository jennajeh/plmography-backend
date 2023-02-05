package kr.jenna.plmography.services.content;

import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.content.UserProfileContentsDto;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
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
        getContentsService = new GetContentsService(
                contentRepository, userRepository);
    }

    @Test
    void topRated() {
        Content content = Content.fake();

        given(contentRepository
                .findAllByPopularityGreaterThanOrderByPopularityDesc(any(Integer.class)))
                .willReturn(List.of(content));

        assertThat(getContentsService.topRated().getContents()).isNotNull();
    }

    @Test
    void filterWithKeyword() {
        Page<Content> pages = new PageImpl<>(List.of(Content.fake()));

        given(contentRepository.findAll(nullable(Specification.class), any(Pageable.class)))
                .willReturn(pages);

        String platform = "netflix";
        String type = "movie";
        String genreId = "16";
        Integer releaseDate = 2022;
        Integer page = 1;
        Integer size = 8;

        ContentsDto contentsDto = getContentsService.filter(
                platform, type, genreId, releaseDate, "", "", page, size);

        assertThat(contentsDto.getContents()).isNotNull();
        assertThat(contentsDto.getContents().get(0).getPlatform()).isEqualTo("netflix");
        assertThat(contentsDto.getPages().getTotalPages()).isEqualTo(1);
    }

    @Test
    void favoriteContents() {
        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        given(contentRepository.findByTmdbId(any(Long.class)))
                .willReturn(Optional.of(Content.fake()));

        Long userId = 1L;
        String favoriteContentId = "1,2,3";

        UserProfileContentsDto userProfileContentsDto =
                getContentsService.favoriteContents(userId, favoriteContentId);

        assertThat(userProfileContentsDto).isNotNull();

        assertThat(userProfileContentsDto.getUserProfileContents()
                .get(0).getImageUrl()).isEqualTo("imageUrl");

        assertThat(userProfileContentsDto.getUserProfileContents()
                .get(0).getContentId()).isEqualTo(1L);
    }

    @Test
    void watchedContents() {
        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        given(contentRepository.findByTmdbId(any(Long.class)))
                .willReturn(Optional.of(Content.fake()));

        Long userId = 1L;
        String watchedContentId = "1,2,3";

        UserProfileContentsDto userProfileContentsDto =
                getContentsService.watchedContents(userId, watchedContentId);

        assertThat(userProfileContentsDto).isNotNull();

        assertThat(userProfileContentsDto.getUserProfileContents()
                .get(0).getImageUrl()).isEqualTo("imageUrl");

        assertThat(userProfileContentsDto.getUserProfileContents()
                .get(0).getContentId()).isEqualTo(1L);
    }

    @Test
    void wishContents() {
        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        given(contentRepository.findByTmdbId(any(Long.class)))
                .willReturn(Optional.of(Content.fake()));

        Long userId = 1L;
        String wishContents = "1,2,3";

        UserProfileContentsDto userProfileContentsDto =
                getContentsService.wishContents(userId, wishContents);

        assertThat(userProfileContentsDto).isNotNull();

        assertThat(userProfileContentsDto.getUserProfileContents()
                .get(0).getImageUrl()).isEqualTo("imageUrl");

        assertThat(userProfileContentsDto.getUserProfileContents()
                .get(0).getContentId()).isEqualTo(1L);
    }
}
