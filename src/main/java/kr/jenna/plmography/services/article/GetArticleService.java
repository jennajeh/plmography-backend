package kr.jenna.plmography.services.article;

import kr.jenna.plmography.dtos.article.ArticleDto;
import kr.jenna.plmography.exceptions.ArticleNotFound;
import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.repositories.ArticleRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public GetArticleService(
            ArticleRepository articleRepository,
            UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ArticleDto detail(Long contentId) {
        Article article = articleRepository.findByContentId(new ContentId(contentId))
                .orElseThrow(() -> new ArticleNotFound());

        return new ArticleDto(
                article.getId(),
                article.getContentId().getValue(),
                article.getTitle().getValue(),
                article.getImage().getValue(),
                article.getArticleBody().getValue(),
                article.getCreatedAt());
    }
}
