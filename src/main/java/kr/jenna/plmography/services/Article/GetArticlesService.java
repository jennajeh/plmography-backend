package kr.jenna.plmography.services.Article;

import kr.jenna.plmography.dtos.Article.ArticleDto;
import kr.jenna.plmography.dtos.Article.ArticlesDto;
import kr.jenna.plmography.dtos.Page.PagesDto;
import kr.jenna.plmography.dtos.User.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ArticleRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetArticlesService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public GetArticlesService(
            ArticleRepository articleRepository,
            UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ArticlesDto list(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Article> articles = articleRepository.findAll(pageable);

        List<ArticleDto> articleDtos = articles.stream()
                .map(article -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFound(userId));

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
                }).toList();

        PagesDto pagesDto = new PagesDto(articles.getTotalPages());

        return new ArticlesDto(articleDtos, pagesDto);
    }
}
