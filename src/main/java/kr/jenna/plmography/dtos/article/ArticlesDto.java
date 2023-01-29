package kr.jenna.plmography.dtos.article;

import kr.jenna.plmography.dtos.page.PagesDto;

import java.util.List;

public class ArticlesDto {
    private List<ArticleDto> articles;
    private PagesDto pages;

    public ArticlesDto() {
    }

    public ArticlesDto(List<ArticleDto> articles, PagesDto pages) {
        this.articles = articles;
        this.pages = pages;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }

    public PagesDto getPages() {
        return pages;
    }
}
