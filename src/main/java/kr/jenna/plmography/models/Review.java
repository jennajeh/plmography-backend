package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.ReviewCreationDto;
import kr.jenna.plmography.dtos.ReviewDto;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private ContentId contentId;

    private Long starRate;

    @Embedded
    private ReviewBody reviewBody;

    private Boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Review() {
    }

    public Review(Long id, UserId userId, ContentId contentId,
                  Long starRate, ReviewBody reviewBody,
                  LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.isDeleted = false;
        this.createdAt = createdAt;
    }

    public Review(Long id, UserId userId, ContentId contentId,
                  Long starRate, ReviewBody reviewBody) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.isDeleted = false;
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

    public Long getStarRate() {
        return starRate;
    }

    public ReviewBody getReviewBody() {
        return reviewBody;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Review fake() {
        return new Review(1L, new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"), LocalDateTime.now());
    }

    public void update(ReviewBody reviewBody) {
        this.reviewBody = reviewBody;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public ReviewDto toReviewDto() {
        return new ReviewDto(id, userId.getValue(), contentId.getValue(),
                starRate, reviewBody.getValue(),
                createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public ReviewCreationDto toCreateDto() {
        return new ReviewCreationDto(id);
    }
}
