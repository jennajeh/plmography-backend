package kr.jenna.plmography.services.Article;

import kr.jenna.plmography.dtos.Article.ArticleDto;
import kr.jenna.plmography.dtos.User.WriterDto;
import kr.jenna.plmography.exceptions.ArticleNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.ContentId;
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

    public ArticleDto detail(Long userId, Long contentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        Article article = articleRepository.findByContentId(new ContentId(contentId))
                .orElseThrow(() -> new ArticleNotFound());

        return new ArticleDto(
                article.getId(),
                new WriterDto(
                        user.getId(),
                        user.getNickname().getValue(),
                        user.getProfileImage().getValue()),
                article.getContentId().getValue(),
                article.getTitle().getValue(),
                article.getImage().getValue(),
                article.getArticleBody().getValue(),
                article.getCreatedAt());
    }
}
