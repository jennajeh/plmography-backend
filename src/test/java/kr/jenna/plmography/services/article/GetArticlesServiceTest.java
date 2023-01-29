package kr.jenna.plmography.services.article;

import kr.jenna.plmography.dtos.article.ArticlesDto;
import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.repositories.ArticleRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetArticlesServiceTest {

    @Test
    void list() {
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        GetArticlesService getArticlesService = new GetArticlesService(
                articleRepository, userRepository);

        given(articleRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(Article.fake())));

        Integer page = 1;
        Integer size = 5;

        ArticlesDto articlesDto = getArticlesService.list(page, size);

        assertThat(articlesDto).isNotNull();
        assertThat(articlesDto.getArticles().get(0).getTitle())
                .isEqualTo(Article.fake().getTitle().getValue());
    }

}
