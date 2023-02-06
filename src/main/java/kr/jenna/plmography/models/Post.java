package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.post.PostCreationDto;
import kr.jenna.plmography.dtos.post.PostModificationResponseDto;
import kr.jenna.plmography.models.vo.Hit;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.PostBody;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.models.vo.UserId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private Title title;

    @Embedded
    private PostBody postBody;

    @Embedded
    private Hit hit;

    @Embedded
    private Image image;

    private Boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Post() {
    }

    public Post(UserId userId, Title title, PostBody postBody, Image image) {
        this.userId = userId;
        this.title = title;
        this.postBody = postBody;
        this.image = image;
        this.hit = new Hit(0L);
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public Title getTitle() {
        return title;
    }

    public PostBody getPostBody() {
        return postBody;
    }

    public Hit getHit() {
        return hit;
    }

    public Image getImage() {
        return image;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void updateHit(Long hit) {
        this.hit = new Hit(hit + 1L);
    }

    public void modify(String title, String postBody, String image) {
        this.title = new Title(title);
        this.postBody = new PostBody(postBody);
        this.image = new Image(image);
    }

    public boolean isWriter(Long userId) {
        return Objects.equals(this.userId.getValue(), userId);
    }

    public void delete() {
        this.isDeleted = true;
    }

    public static Post fake() {
        return new Post(
                new UserId(1L),
                new Title("제목"),
                new PostBody("첫 글 작성"),
                new Image("image"));
    }

    public PostCreationDto toPostCreationDto() {
        return new PostCreationDto(id, isDeleted);
    }

    public PostModificationResponseDto toPostModificationResponseDto() {
        return new PostModificationResponseDto(id);
    }
}
