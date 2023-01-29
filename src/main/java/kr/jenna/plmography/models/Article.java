package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.models.vo.ArticleBody;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.models.vo.UserId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private ContentId contentId;

    @Embedded
    private Title title;

    @Embedded
    @Column(length = 4000)
    private ArticleBody articleBody;

    @Embedded
    private Image image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Article() {
    }

    public Article(UserId userId,
                   ContentId contentId, Title title,
                   ArticleBody articleBody,
                   Image image) {
        this.userId = userId;
        this.contentId = contentId;
        this.title = title;
        this.articleBody = articleBody;
        this.image = image;
    }

    public static Article fake() {
        return new Article(
                new UserId(1L),
                new ContentId(1L),
                new Title("더 글로리"),
                new ArticleBody("고등학교 시절, 끔찍한 괴롭힘에 시달렸던 여자. "
                        + "많은 시간이 흐른 후, 가해자들을 응징하기 위해 그녀가 치밀한 복수를 감행한다."),
                new Image("image"));
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public ContentId getContentId() {
        return contentId;
    }

    public Title getTitle() {
        return title;
    }

    public ArticleBody getArticleBody() {
        return articleBody;
    }

    public Image getImage() {
        return image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
