package kr.jenna.plmography.services.article;

import kr.jenna.plmography.dtos.article.ArticleDto;
import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.repositories.ArticleRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetArticleServiceTest {

    @Test
    void detail() {
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetArticleService getArticleService = new GetArticleService(
                articleRepository, userRepository);

        given(articleRepository.findByContentId(any(ContentId.class)))
                .willReturn(Optional.of(Article.fake()));

        ArticleDto articleDto = getArticleService.detail(1L);

        assertThat(articleDto).isNotNull();
        assertThat(articleDto.getTitle()).isEqualTo(Article.fake().getTitle().getValue());
    }
}
