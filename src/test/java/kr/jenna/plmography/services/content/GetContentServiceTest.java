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

    @Test
    void userProfile() {
//        Set<Long> test = new HashSet<>();
//
//        test.add(1L);
//        test.add(2L);
//        test.add(3L);
//        test.add(4L);
//
//        assertThat(test.stream().collect(Collectors.toSet()))
//                .isEqualTo(Set.of(1L));

//        given(userRepository.findById(1L)).willReturn(Optional.of(User.fake()));
//
//        Set<FavoriteContentId> favoriteContentIds = new HashSet<>();
//
//        favoriteContentIds.add(new FavoriteContentId(1L));
//        favoriteContentIds.add(new FavoriteContentId(2L));
//        favoriteContentIds.add(new FavoriteContentId(3L));
//        favoriteContentIds.add(new FavoriteContentId(4L));
//        favoriteContentIds.add(new FavoriteContentId(5L));
//
//        given(contentRepository.findAllByTmdbId(
//                favoriteContentIds.stream().collect(Collectors.toSet())))
//                .willReturn(Optional.of(User.fake()));

    }
}
