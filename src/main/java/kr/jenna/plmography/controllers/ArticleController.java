package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Article.ArticleDto;
import kr.jenna.plmography.dtos.Article.ArticlesDto;
import kr.jenna.plmography.exceptions.ArticleNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.services.Article.GetArticleService;
import kr.jenna.plmography.services.Article.GetArticlesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private GetArticlesService getArticlesService;
    private GetArticleService getArticleService;

    public ArticleController(
            GetArticlesService getArticlesService,
            GetArticleService getArticleService) {
        this.getArticlesService = getArticlesService;
        this.getArticleService = getArticleService;
    }

    @GetMapping
    public ArticlesDto list(
            @RequestAttribute Long userId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return getArticlesService.list(userId, page, size);
    }

    @GetMapping("/{contentId}")
    public ArticleDto detail(
            @RequestAttribute Long userId,
            @PathVariable Long contentId) {
        return getArticleService.detail(userId, contentId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }

    @ExceptionHandler(ArticleNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String articleNotFound() {
        return "Article not found!";
    }
}
