package kr.jenna.plmography.dtos.Article;

import kr.jenna.plmography.dtos.User.WriterDto;

import java.time.LocalDateTime;

public class ArticleDto {
    private Long id;
    private WriterDto writer;
    private Long contentId;
    private String title;
    private String image;
    private String articleBody;
    private LocalDateTime createdAt;

    public ArticleDto() {
    }

    public ArticleDto(
            Long id,
            WriterDto writer,
            Long contentId,
            String title,
            String image, String articleBody,
            LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.contentId = contentId;
        this.title = title;
        this.image = image;
        this.articleBody = articleBody;
        this.createdAt = createdAt;
    }

    public ArticleDto(
            Long id,
            Long contentId,
            String title,
            String image, String articleBody,
            LocalDateTime createdAt) {
        this.id = id;
        this.contentId = contentId;
        this.title = title;
        this.image = image;
        this.articleBody = articleBody;
        this.createdAt = createdAt;
    }

    public static ArticleDto fake() {
        return new ArticleDto(
                1L,
                new WriterDto(1L, "전제나", "image"),
                1L,
                "더 글로리",
                "사진",
                "복수극",
                LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public WriterDto getWriter() {
        return writer;
    }

    public Long getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
