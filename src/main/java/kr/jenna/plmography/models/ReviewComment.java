package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentCreationDto;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.ReviewCommentBody;
import kr.jenna.plmography.models.vo.UserId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private PostId postId;

    @Embedded
    private ReviewCommentBody reviewCommentBody;

    private boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ReviewComment() {
    }

    public ReviewComment(Long id,
                         UserId userId,
                         PostId postId,
                         ReviewCommentBody reviewCommentBody,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.reviewCommentBody = reviewCommentBody;
        this.isDeleted = false;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ReviewComment(UserId userId,
                         PostId postId,
                         ReviewCommentBody reviewCommentBody,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
        this.userId = userId;
        this.postId = postId;
        this.reviewCommentBody = reviewCommentBody;
        this.isDeleted = false;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public ReviewCommentBody getReviewCommentBody() {
        return reviewCommentBody;
    }

    public PostId getPostId() {
        return postId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ReviewComment fake() {
        return new ReviewComment(
                1L,
                new UserId(1L),
                new PostId(1L),
                new ReviewCommentBody("reply"),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public void modify(ReviewCommentBody reviewCommentBody) {
        this.reviewCommentBody = reviewCommentBody;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isWriter(Long userId) {
        return this.userId.getValue() == userId;
    }

    public ReviewCommentCreationDto toCreateDto() {
        return new ReviewCommentCreationDto(id);
    }
}
