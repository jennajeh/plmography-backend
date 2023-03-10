package kr.jenna.plmography.services.article;

import kr.jenna.plmography.dtos.article.ArticleDto;
import kr.jenna.plmography.dtos.article.ArticlesDto;
import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.repositories.ArticleRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public ArticlesDto list(Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Article> articles = articleRepository.findAll(pageable);

        List<ArticleDto> articleDtos = articles.stream()
                .map(article -> {
                    return new ArticleDto(
                            article.getId(),
                            article.getContentId().getValue(),
                            article.getTitle().getValue(),
                            article.getImage().getValue(),
                            article.getArticleBody().getValue(),
                            article.getCreatedAt());
                }).toList();

        PagesDto pagesDto = new PagesDto(articles.getTotalPages());

        return new ArticlesDto(articleDtos, pagesDto);
    }

    public ArticlesDto sortByCreatedAt() {
        List<Article> articles = articleRepository.findTop4ByOrderByCreatedAtDesc();

        List<ArticleDto> articleDtos = articles.stream()
                .map(article -> {
                    return new ArticleDto(
                            article.getId(),
                            article.getContentId().getValue(),
                            article.getTitle().getValue(),
                            article.getImage().getValue(),
                            article.getArticleBody().getValue(),
                            article.getCreatedAt());
                }).collect(Collectors.toList());

        return new ArticlesDto(articleDtos);
    }
}
